package com.carhouse.rest.controllerIT;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CarFeatureControllerTestIT {

    private static final String HOST = "http://localhost:8080";
    private static final String CAR_FEATURE_LIST_GET_URL = "/carSale/car/2/carFeature";
    private static final String FEATURE_LIST_GET_URL = "/carSale/car/carFeature";

    RestTemplate restTemplate = new RestTemplate();

    @Test
    void getCarFeatures() {
        ResponseEntity<String> response = restTemplate.getForEntity(HOST + CAR_FEATURE_LIST_GET_URL, String.class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void getAllFeatures() {
        ResponseEntity<String> response = restTemplate.getForEntity(HOST + FEATURE_LIST_GET_URL, String.class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }
}
