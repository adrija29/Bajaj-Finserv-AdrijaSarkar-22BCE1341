# Bajaj-Finserv-AdrijaSarkar-22BCE1341

# Bajaj Finserv Health - Java Hiring Challenge

This project is a Spring Boot application developed as a solution for the Bajaj Finserv Health hiring challenge. The application automates the process of retrieving a programming challenge, solving it, and submitting the solution via API calls, all triggered on application startup.

-----

## Features

  * **Automated Startup Logic**: The application uses `CommandLineRunner` to execute its main logic as soon as it starts, requiring no manual triggers or API endpoints[cite: 36].
  * **Dynamic Webhook Generation**: On startup, the application makes a POST request to a specified endpoint to generate a unique webhook URL and an access token for submission[cite: 4, 9].
  * **Conditional Problem Solving**: It determines which SQL problem to solve based on the user's registration number (Odd or Even)[cite: 19, 20].
  * **Secure Submission**: The final SQL query is submitted to the generated webhook URL using a JWT access token in the Authorization header for security[cite: 27, 37].

-----

## How to Run

There are two ways to run this application:

### 1\. Using the Pre-built JAR

The easiest way is to run the packaged JAR file directly from your terminal.

```bash
java -jar bajaj-finserv-test-0.0.1-SNAPSHOT.jar
```

### 2\. Building from Source

If you want to build the project from the source code, you'll need Java (17 or newer) and Maven installed.

1.  **Clone the repository:**

    ```bash
    git clone https://github.com/adrija29/Bajaj-Finserv-AdrijaSarkar-22BCE1341.git
    cd Bajaj-Finserv-AdrijaSarkar-22BCE1341
    ```

2.  **Build the project using the Maven Wrapper:**

    ```bash
    ./mvnw.cmd clean package
    ```

3.  **Run the generated JAR file:**

    ```bash
    java -jar target/bajaj-finserv-test-0.0.1-SNAPSHOT.jar
    ```

-----

## Configuration

The application is configured with the user's personal details directly in the source code.

  * **Location**: `src/main/java/com/adrija/bajaj_finserv_test/AppRunner.java`
  * **Details**: The `name`, `regNo`, and `email` are set within the `generateWebhook()` method.

<!-- end list -->

```java
// User details for the initial API call
Map<String, String> requestBody = new HashMap<>();
requestBody.put("name", "Adrija Sarkar");
requestBody.put("regNo", "22BCE1341");
requestBody.put("email", "adrija.sarkar2022@vitstudent.ac.in");
```
