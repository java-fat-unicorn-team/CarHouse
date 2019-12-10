package com.carhouse.rest.controllerIT;

import com.carhouse.model.CarModel;
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
import static org.junit.jupiter.api.Assertions.assertTrue;

class CarModelControllerTestIT {

    private static final String HOST = "http://localhost:8086";
    private static final String CAR_MODEL_LIST_GET_URL = "/carSale/car/carModel/list/";
    private static final String CAR_MODEL_GET_URL = "/carSale/car/carModel/";

    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getCarModels() {
        ResponseEntity<String> response = restTemplate.getForEntity(HOST + CAR_MODEL_LIST_GET_URL + 2,
                String.class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void getCarModelsOfNotExistCarMake() throws IOException {
        int carMakeId = 32;
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> restTemplate.getForEntity(HOST + CAR_MODEL_LIST_GET_URL + carMakeId, String.class));
        ExceptionJSONResponse response = objectMapper.readValue(exception.getResponseBodyAsString(),
                ExceptionJSONResponse.class);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        assertEquals("there is not car make with id = " + carMakeId, response.getMessages().get(0));
        assertEquals(CAR_MODEL_LIST_GET_URL + carMakeId, response.getPath());
    }

    @Test
    void getCarModelsValidationError() throws IOException {
        int carMakeId = -12;
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> restTemplate.getForEntity(HOST + CAR_MODEL_LIST_GET_URL + carMakeId, String.class));
        ExceptionJSONResponse response = objectMapper.readValue(exception.getResponseBodyAsString(),
                ExceptionJSONResponse.class);
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertEquals("car make id can't be negative", response.getMessages().get(0));
        assertEquals(CAR_MODEL_LIST_GET_URL + carMakeId, response.getPath());
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
        assertTrue(exception.getResponseBodyAsString().contains("there is not car model with id = " + 32));
    }

    @Test
    void getCarModelValidationError() throws IOException {
        int carModelId = -12;
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> restTemplate.getForEntity(HOST + CAR_MODEL_GET_URL + carModelId, String.class));
        ExceptionJSONResponse response = objectMapper.readValue(exception.getResponseBodyAsString(),
                ExceptionJSONResponse.class);
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertEquals("car model id can't be negative", response.getMessages().get(0));
        assertEquals(CAR_MODEL_GET_URL + carModelId, response.getPath());
    }
}
