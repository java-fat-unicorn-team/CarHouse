package com.carhouse.rest.controllerIT;

import com.carhouse.model.Car;
import com.carhouse.model.CarSale;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CarSaleControllerTestIT {

    private static final String HOST = "http://localhost:8086";
    private static final String CAR_SALE_LIST_GET_URL = "/carSale";
    private static final String CAR_SALE_DTO_LIST_GET_URL = "/carSale/dto";
    private static final String CAR_SALE_GET_URL = "/carSale/";
    private static final String CAR_SALE_ADD_URL = "/carSale";
    private static final String CAR_SALE_UPDATE_URL = "/carSale";
    private static final String CAR_SALE_DELETE_URL = "/carSale/";

    RestTemplate restTemplate = new RestTemplate();

    @Test
    void getCarSales() {
        ResponseEntity<String> response = restTemplate.getForEntity(HOST + CAR_SALE_LIST_GET_URL, String.class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void getCarSalesDto() {
        ResponseEntity<String> response = restTemplate.getForEntity(HOST + CAR_SALE_DTO_LIST_GET_URL, String.class);
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
    void getNotExistCarSale() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> restTemplate.getForEntity(HOST + CAR_SALE_GET_URL + 150, String.class));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertTrue(exception.getResponseBodyAsString().contains("there is not car sale with id = " + 150));
    }

    @Test
    void addCarSale() {
        CarSale carSale = restTemplate.getForObject(HOST + CAR_SALE_GET_URL + 3, CarSale.class);
        carSale.setPrice(new BigDecimal(23300));
        carSale.setCar(new Car(2));
        HttpEntity<CarSale> request = new HttpEntity<>(carSale);
        ResponseEntity<String> response = restTemplate.postForEntity(HOST + CAR_SALE_ADD_URL,
                request, String.class);
        assertEquals(200, response.getStatusCodeValue());
        Integer id = Integer.valueOf(response.getBody());
        assertTrue(id > 0);
    }

    @Test
    void addCarSaleWithWrongReference() {
        CarSale carSale = restTemplate.getForObject(HOST + CAR_SALE_GET_URL + 3, CarSale.class);
        carSale.setCar(new Car(200));
        HttpEntity<CarSale> request = new HttpEntity<>(carSale);
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> restTemplate.postForEntity(HOST + CAR_SALE_ADD_URL, request, String.class));
        assertEquals(HttpStatus.FAILED_DEPENDENCY, exception.getStatusCode());
        assertTrue(exception.getResponseBodyAsString().contains("there is wrong references in your car sale"));
    }

    @Test
    void updateCarSale() {
        int carSaleId = 3;
        int newCarId = 2;
        BigDecimal newCarPrice = new BigDecimal(23300);
        CarSale carSale = restTemplate.getForObject(HOST + CAR_SALE_GET_URL + carSaleId, CarSale.class);
        carSale.setPrice(newCarPrice);
        carSale.setCar(new Car(2));
        HttpEntity<CarSale> request = new HttpEntity<>(carSale);
        ResponseEntity response = restTemplate.exchange(HOST + CAR_SALE_UPDATE_URL, HttpMethod.PUT,
                request, CarSale.class);
        assertEquals(200, response.getStatusCodeValue());
        CarSale updatedCarSale = restTemplate.getForObject(HOST + CAR_SALE_GET_URL + carSaleId, CarSale.class);
        assertEquals(newCarPrice, updatedCarSale.getPrice());
        assertEquals(newCarId, updatedCarSale.getCar().getCarId());
    }

    @Test
    void updateCarSaleWithWrongReference() {
        CarSale carSale = restTemplate.getForObject(HOST + CAR_SALE_GET_URL + 3, CarSale.class);
        carSale.setCar(new Car(200));
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
