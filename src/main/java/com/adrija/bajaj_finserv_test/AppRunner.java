// src/main/java/com/adrija/bajajfinservtest/runner/AppRunner.java
package com.adrija.bajajfinservtest.runner;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AppRunner implements CommandLineRunner {

    private final RestTemplate restTemplate = new RestTemplate();

    // !!! IMPORTANT !!!
    // Solve Question 1 from the Google Drive link and paste your final SQL query here.
    // Your regNo ends in '41' (Odd), so you MUST solve Question 1.
    private static final String SQL_QUERY_ODD = "SELECT P.AMOUNT AS SALARY, CONCAT(E.FIRST_NAME, ' ', E.LAST_NAME) AS NAME, TIMESTAMPDIFF(YEAR, E.DOB, CURDATE()) AS AGE, D.DEPARTMENT_NAME FROM PAYMENTS AS P JOIN EMPLOYEE AS E ON P.EMP_ID = E.EMP_ID JOIN DEPARTMENT AS D ON E.DEPARTMENT = D.DEPARTMENT_ID WHERE EXTRACT(DAY FROM P.PAYMENT_TIME) <> 1 ORDER BY P.AMOUNT DESC LIMIT 1;";

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Application starting... Initiating API calls.");

        // Step 1: Generate the webhook and get the access token [cite: 4]
        Map<String, String> webhookResponse = generateWebhook();

        if (webhookResponse != null) {
            String webhookUrl = webhookResponse.get("webhookUrl");
            String accessToken = webhookResponse.get("accessToken");

            System.out.println("Successfully received Webhook URL: " + webhookUrl); // [cite: 17]
            System.out.println("Successfully received Access Token."); // [cite: 18]

            // Step 2: Submit your solved SQL query [cite: 5, 6]
            submitSolution(webhookUrl, accessToken);
        } else {
            System.err.println("Failed to generate webhook. Aborting.");
        }
    }

    private Map<String, String> generateWebhook() {
        String url = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA"; // [cite: 9]

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Your personal details for the request body [cite: 10-15]
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "Adrija Sarkar");
        requestBody.put("regNo", "22BCE1341");
        requestBody.put("email", "adrija.sarkar2022@vitstudent.ac.in");

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        try {
            System.out.println("Sending POST request to generate webhook...");
            ResponseEntity<Map> response = restTemplate.postForEntity(url, requestEntity, Map.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                System.err.println("Error generating webhook. Status: " + response.getStatusCode());
                return null;
            }
        } catch (Exception e) {
            System.err.println("An exception occurred during webhook generation: " + e.getMessage());
            return null;
        }
    }

    private void submitSolution(String webhookUrl, String accessToken) {
        // Your registration number determines the question. The last two digits are 41 (Odd).
        // Therefore, you must solve Question 1. [cite: 19, 20]
        System.out.println("Registration number ends in an odd number. Preparing Question 1 query for submission.");
        String finalQuery = SQL_QUERY_ODD;

        // Set up headers: Content-Type and Authorization with the JWT token [cite: 26-28]
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", accessToken); // [cite: 27, 37]

        // Set up the request body with the final SQL query [cite: 29-32]
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("finalQuery", finalQuery);

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        try {
            System.out.println("Sending POST request to submit solution...");
            ResponseEntity<String> response = restTemplate.postForEntity(webhookUrl, requestEntity, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("SUCCESS! Solution submitted successfully!");
                System.out.println("Response: " + response.getBody());
            } else {
                System.err.println("Error submitting solution. Status: " + response.getStatusCode());
                System.err.println("Response Body: " + response.getBody());
            }
        } catch (Exception e) {
            System.err.println("An exception occurred during solution submission: " + e.getMessage());
        }
    }
}