package com.carhouse.rest.controllerIT;

import com.carhouse.model.CarMake;
import com.carhouse.rest.response.ExceptionJSONResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CarMakeControllerTestIT {

    private static final String HOST = "http://localhost:8086";
    private static final String CAR_MAKE_LIST_GET_URL = "/carSale/car/carModel/carMake";
    private static final String CAR_MAKE_GET_URL = "/carSale/car/carModel/carMake/";

    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getCarMakes() {
        ResponseEntity<String> response = restTemplate.getForEntity(HOST + CAR_MAKE_LIST_GET_URL, String.class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void getCarMake() {
        ResponseEntity<CarMake> response = restTemplate.getForEntity(HOST + CAR_MAKE_GET_URL + 1, CarMake.class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void getNotExistCarMake() throws IOException {
        int carMakeId = 32;
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> restTemplate.getForEntity(HOST + CAR_MAKE_GET_URL + carMakeId, String.class));
        ExceptionJSONResponse response = objectMapper.readValue(exception.getResponseBodyAsString(),
                ExceptionJSONResponse.class);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        assertEquals("there is not car make with id = " + carMakeId, response.getMessages().get(0));
        assertEquals(CAR_MAKE_GET_URL + carMakeId, response.getPath());
    }

    @Test
    void getCarMakeValidationError() throws IOException {
        int carMakeId = -12;
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> restTemplate.getForEntity(HOST + CAR_MAKE_GET_URL + carMakeId, String.class));
        ExceptionJSONResponse response = objectMapper.readValue(exception.getResponseBodyAsString(),
                ExceptionJSONResponse.class);
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertEquals("car make id can't be negative", response.getMessages().get(0));
        assertEquals(CAR_MAKE_GET_URL + carMakeId, response.getPath());
    }
}