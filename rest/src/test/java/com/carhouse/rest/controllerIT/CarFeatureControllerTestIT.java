package com.carhouse.rest.controllerIT;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class CarFeatureControllerTestIT {

    private static final String HOST = "http://localhost:8086";
    private static final String CAR_FEATURE_LIST_GET_URL = "/carSale/car/2/carFeature";
    private static final String FEATURE_LIST_OF_NOT_EXIST_CAR_GET_URL = "/carSale/car/32/carFeature";
    private static final String FEATURE_LIST_GET_URL = "/carSale/car/carFeature";
    private static String ERROR_RESPONSE_REGEX;

    private RestTemplate restTemplate = new RestTemplate();

    @BeforeAll
    static void getProperty() throws IOException {
        Properties properties = new Properties();
        properties.load(CarControllerTestIT.class.getClassLoader().getResourceAsStream("test.properties"));
        ERROR_RESPONSE_REGEX = properties.getProperty("error.response.regexp");
    }

    @Test
    void getCarFeatures() {
        ResponseEntity<String> response = restTemplate.getForEntity(HOST + CAR_FEATURE_LIST_GET_URL, String.class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void getCarFeaturesOfNotExistCar() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> restTemplate.getForEntity(HOST
                        + FEATURE_LIST_OF_NOT_EXIST_CAR_GET_URL, String.class));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertTrue(exception.getResponseBodyAsString().matches(ERROR_RESPONSE_REGEX));
        assertTrue(exception.getResponseBodyAsString().contains("there is not car with id = " + 32));
    }

    @Test
    void getAllFeatures() {
        ResponseEntity<String> response = restTemplate.getForEntity(HOST + FEATURE_LIST_GET_URL, String.class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }
}
