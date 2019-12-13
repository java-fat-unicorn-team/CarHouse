package com.carhouse.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * The type Car house.
 */
@SpringBootApplication
@PropertySource("application-custom.properties")
public class CarHouse {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(CarHouse.class, args);
    }
}
