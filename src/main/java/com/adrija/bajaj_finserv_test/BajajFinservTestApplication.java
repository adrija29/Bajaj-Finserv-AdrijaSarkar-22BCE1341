package com.adrija.bajaj_finserv_test;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder; // Add this import

@SpringBootApplication
public class BajajFinservTestApplication {

    public static void main(String[] args) {
        // This new code runs the application without starting a web server
        new SpringApplicationBuilder(BajajFinservTestApplication.class)
                .web(WebApplicationType.NONE) 
                .run(args);
    }

}