package org.ShufanServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class serves as the entry point for the REST API created using Spring Boot and Maven.
 * It contains the main method to run the Spring Boot application.
 * <p>
 * Author: Shufan Sun
 */
@SpringBootApplication
public class StartApi {
    public static void main(String[] args) {
        // Run the Spring Boot application
        SpringApplication.run(StartApi.class, args);
    }
}
