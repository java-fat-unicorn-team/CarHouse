package com.carhouse.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("application-custom.properties")
public class CarHouse {

    public static void main(String[] args) {
        SpringApplication.run(CarHouse.class, args);
    }
}
