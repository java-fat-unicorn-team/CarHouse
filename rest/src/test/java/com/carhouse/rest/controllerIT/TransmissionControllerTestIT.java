package com.carhouse.rest.controllerIT;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TransmissionControllerTestIT {

    private static final String HOST = "http://localhost:8086";
    private static final String TRANSMISSION_LIST_GET_URL = "/carSale/car/transmission";

    RestTemplate restTemplate = new RestTemplate();

    @Test
    void getTransmissions() {
        ResponseEntity<String> response = restTemplate.getForEntity(HOST + TRANSMISSION_LIST_GET_URL, String.class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }
}