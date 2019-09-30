package com.carhouse.rest.controllerIT;

import com.carhouse.model.CarSale;
import com.carhouse.rest.response.ExceptionJSONResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CarSaleControllerTestIT {

    private static final String HOST = "http://localhost:8086";
    private static final String CAR_SALE_LIST_GET_URL = "/carSale";
    private static final String CAR_SALE_GET_URL = "/carSale/";
    private static final String CAR_SALE_ADD_URL = "/carSale";
    private static final String CAR_SALE_UPDATE_URL = "/carSale";
    private static final String CAR_SALE_DELETE_URL = "/carSale/";

    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getListCarSales() {
        ResponseEntity<String> response = restTemplate.getForEntity(HOST + CAR_SALE_LIST_GET_URL, String.class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void getCarSale() {
        ResponseEntity<CarSale> response = restTemplate.getForEntity(HOST + CAR_SALE_GET_URL + 3, CarSale.class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void getNotExistCarSale() throws JsonProcessingException {
        int carSaleId = 150;
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> restTemplate.getForEntity(HOST + CAR_SALE_GET_URL + carSaleId, String.class));
        ExceptionJSONResponse response = objectMapper.readValue(exception.getResponseBodyAsString(),
                ExceptionJSONResponse.class);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        assertEquals("there is not car sale with id = " + carSaleId, response.getMessage());
        assertEquals(CAR_SALE_GET_URL + carSaleId, response.getPath());
    }

    @Test
    void addCarSale() {
        CarSale carSale = restTemplate.getForObject(HOST + CAR_SALE_GET_URL + 3, CarSale.class);
        carSale.setPrice(new BigDecimal(23300));
        carSale.setDate(new Date());
        HttpEntity<CarSale> request = new HttpEntity<>(carSale);
        ResponseEntity<String> response = restTemplate.postForEntity(HOST + CAR_SALE_ADD_URL,
                request, String.class);
        assertEquals(200, response.getStatusCodeValue());
        Integer id = Integer.valueOf(response.getBody());
        assertTrue(id > 0);
    }

    @Test
    void addCarSaleWithWrongReference() throws JsonProcessingException {
        CarSale carSale = restTemplate.getForObject(HOST + CAR_SALE_GET_URL + 3, CarSale.class);
        carSale.getUser().setUserId(200);
        HttpEntity<CarSale> request = new HttpEntity<>(carSale);
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> restTemplate.postForEntity(HOST + CAR_SALE_ADD_URL, request, String.class));
        ExceptionJSONResponse response = objectMapper.readValue(exception.getResponseBodyAsString(),
                ExceptionJSONResponse.class);
        assertEquals(HttpStatus.FAILED_DEPENDENCY.value(), response.getStatus());
        assertEquals("there is wrong references in your car sale", response.getMessage());
        assertEquals(CAR_SALE_ADD_URL, response.getPath());
    }

    @Test
    void updateCarSale() {
        int carSaleId = 3;
        int newCarModelId = 2;
        BigDecimal newCarPrice = new BigDecimal(23300);
        CarSale carSale = restTemplate.getForObject(HOST + CAR_SALE_GET_URL + carSaleId, CarSale.class);
        carSale.setPrice(newCarPrice);
        carSale.getCar().getCarModel().setCarModelId(newCarModelId);
        HttpEntity<CarSale> request = new HttpEntity<>(carSale);
        ResponseEntity response = restTemplate.exchange(HOST + CAR_SALE_UPDATE_URL, HttpMethod.PUT,
                request, CarSale.class);
        assertEquals(200, response.getStatusCodeValue());
        CarSale updatedCarSale = restTemplate.getForObject(HOST + CAR_SALE_GET_URL + carSaleId, CarSale.class);
        assertEquals(newCarPrice, updatedCarSale.getPrice());
        assertEquals(newCarModelId, updatedCarSale.getCar().getCarModel().getCarModelId());
    }

    @Test
    void updateCarSaleWithWrongReference() {
        CarSale carSale = restTemplate.getForObject(HOST + CAR_SALE_GET_URL + 3, CarSale.class);
        carSale.getUser().setUserId(200);
        HttpEntity<CarSale> request = new HttpEntity<>(carSale);
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> restTemplate.exchange(HOST + CAR_SALE_UPDATE_URL, HttpMethod.PUT, request, String.class));
        assertEquals(HttpStatus.FAILED_DEPENDENCY, exception.getStatusCode());
        assertTrue(exception.getResponseBodyAsString().contains("there is wrong references in your car sale"));
    }

    @Test
    void updateNotExistCarSale() {
        CarSale carSale = restTemplate.getForObject(HOST + CAR_SALE_GET_URL + 3, CarSale.class);
        carSale.setCarSaleId(37);
        HttpEntity<CarSale> request = new HttpEntity<>(carSale);
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> restTemplate.exchange(HOST + CAR_SALE_UPDATE_URL, HttpMethod.PUT, request, String.class));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertTrue(exception.getResponseBodyAsString().contains("there is not car sale with id = " + 37));
    }

    @Test
    void deleteCarSale() {
        int carSaleId = 4;
        ResponseEntity response = restTemplate.exchange(HOST + CAR_SALE_DELETE_URL + carSaleId,
                HttpMethod.DELETE, null, String.class);
        assertEquals(200, response.getStatusCodeValue());
        assertThrows(HttpClientErrorException.NotFound.class, () -> restTemplate.getForEntity(HOST
                + CAR_SALE_DELETE_URL + carSaleId, CarSale.class));
    }

    @Test
    void deleteNotExistCarSale() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> restTemplate.exchange(HOST + CAR_SALE_DELETE_URL + 40,
                        HttpMethod.DELETE, null, String.class));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertTrue(exception.getResponseBodyAsString().contains("there is not car sale with id = "
                + 40 + " to delete"));
    }
}
