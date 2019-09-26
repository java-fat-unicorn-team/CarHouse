package com.carhouse.rest.controllerIT;

import com.carhouse.model.CarModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class CarModelControllerTestIT {

    private static final String HOST = "http://localhost:8086";
    private static final String CAR_MODEL_LIST_GET_URL = "/carSale/car/carModel/list/";
    private static final String CAR_MODEL_GET_URL = "/carSale/car/carModel/";
    private static String ERROR_RESPONSE_REGEX;

    private RestTemplate restTemplate = new RestTemplate();

    @BeforeAll
    static void getProperty() throws IOException {
        Properties properties = new Properties();
        properties.load(CarControllerTestIT.class.getClassLoader().getResourceAsStream("test.properties"));
        ERROR_RESPONSE_REGEX = properties.getProperty("error.response.regexp");
    }

    @Test
    void getCarModels() {
        ResponseEntity<String> response = restTemplate.getForEntity(HOST + CAR_MODEL_LIST_GET_URL + 2,
                String.class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void getCarModelsOfNotExistCarMake() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> restTemplate.getForEntity(HOST + CAR_MODEL_LIST_GET_URL + 32, String.class));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertTrue(exception.getResponseBodyAsString().matches(ERROR_RESPONSE_REGEX));
        assertTrue(exception.getResponseBodyAsString().contains("there is not car make with id = " + 32));
    }

    @Test
    void getCarModel() {
        ResponseEntity<CarModel> response = restTemplate.getForEntity(HOST + CAR_MODEL_GET_URL + 1, CarModel.class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void getNotExistCarModel() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> restTemplate.getForEntity(HOST + CAR_MODEL_GET_URL + 32, String.class));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertTrue(exception.getResponseBodyAsString().matches(ERROR_RESPONSE_REGEX));
        assertTrue(exception.getResponseBodyAsString().contains("there is not car model with id = " + 32));
    }
}
