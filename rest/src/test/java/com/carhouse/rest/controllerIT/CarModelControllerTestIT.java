package com.carhouse.rest.controllerIT;

import com.carhouse.model.CarModel;
import com.carhouse.rest.response.ExceptionJSONResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class CarModelControllerTestIT {

    private static final String HOST = "http://localhost:8086";
    private static final String CAR_MODEL_LIST_GET_URL = "/carSale/car/carModel/list/";
    private static final String CAR_MODEL_GET_URL = "/carSale/car/carModel/";

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    void getCarModels() {
        ResponseEntity<String> response = restTemplate.getForEntity(HOST + CAR_MODEL_LIST_GET_URL + 2,
                String.class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void getCarModelsOfNotExistCarMake() throws JsonProcessingException {
        int carMakeId = 32;
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> restTemplate.getForEntity(HOST + CAR_MODEL_LIST_GET_URL + carMakeId, String.class));
        ExceptionJSONResponse response = new ObjectMapper().readValue(exception.getResponseBodyAsString(),
                ExceptionJSONResponse.class);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        assertEquals("there is not car make with id = " + carMakeId, response.getMessage());
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
}
