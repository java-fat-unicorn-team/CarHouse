package com.carhouse.rest.controllerIT;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CarModelControllerTestIT {

    private static final String HOST = "http://localhost:8080";
    private static final String CAR_MODEL_LIST_GET_URL = "/carSale/car/carModel";

    RestTemplate restTemplate = new RestTemplate();

    @Test
    void getCarModels() {
        ResponseEntity<String> response = restTemplate.getForEntity(HOST + CAR_MODEL_LIST_GET_URL, String.class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }
}