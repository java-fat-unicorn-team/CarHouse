package com.carhouse.rest.controllerIT;

import com.carhouse.model.CarMake;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


class CarMakeControllerTestIT {

    private static final String HOST = "http://localhost:8086";
    private static final String CAR_MAKE_LIST_GET_URL = "/carSale/car/carModel/carMake";
    private static final String CAR_MAKE_GET_URL = "/carSale/car/carModel/carMake/";

    RestTemplate restTemplate = new RestTemplate();

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
    void getNotExistCarMake() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> restTemplate.getForEntity(HOST + CAR_MAKE_GET_URL + 32, String.class));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals(exception.getResponseBodyAsString(),"there is not car make with id = " + 32);
    }
}