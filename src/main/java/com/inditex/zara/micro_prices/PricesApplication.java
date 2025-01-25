package com.inditex.zara.micro_prices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Main class for the PricingApplication.
 * This is a Spring Boot application that provides a REST service
 * for querying product pricing based on brand, product ID, and date.
 *
 * The application follows a Hexagonal Architecture design pattern,
 * separating business logic, application services, and infrastructure concerns.
 *
 * Lombok is used to reduce boilerplate code.
 */
@SpringBootApplication
public class PricesApplication {
    /**
     * The entry point of the Spring Boot application.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(PricesApplication.class, args);
    }
}
