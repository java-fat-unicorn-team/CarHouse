package com.carhouse.rest.controllerIT;

import com.carhouse.model.Car;
import com.carhouse.model.CarSale;
import com.carhouse.rest.controller.CarSaleController;
import com.carhouse.rest.handler.RestExceptionHandler;
import com.carhouse.rest.testConfig.RestTestConfig;
import com.carhouse.service.CarSaleService;
import com.carhouse.service.exception.WrongReferenceException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@ContextConfiguration(classes = RestTestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CarSaleControllerTestIT {

    private static final String HOST = "http://localhost:8080";
    private static final String CAR_SALE_LIST_GET_URL = "/carSale";
    private static final String CAR_SALE_GET_URL = "/carSale/";
    private static final String CAR_SALE_ADD_URL = "/carSale";
    private static final String CAR_SALE_UPDATE_URL = "/carSale";
    private static final String CAR_SALE_DELETE_URL = "/carSale/";

    RestTemplate restTemplate = new RestTemplate();

    @Test
    void getCarSales()  {
        ResponseEntity<String> response = restTemplate.getForEntity(HOST + CAR_SALE_LIST_GET_URL, String.class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void getCarSale()  {
        ResponseEntity<CarSale> response = restTemplate.getForEntity(HOST + CAR_SALE_GET_URL + 3, CarSale.class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void getNotExistCarSale() {
        assertThrows(HttpClientErrorException.NotFound.class, () -> restTemplate.getForEntity(HOST
                + CAR_SALE_GET_URL + 150, Car.class));
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
        restTemplate.delete(HOST + CAR_SALE_DELETE_URL + id);
    }

    @Test
    void addCarSaleWithWrongReference() {
        CarSale carSale = restTemplate.getForObject(HOST + CAR_SALE_GET_URL + 3, CarSale.class);
        carSale.setCar(new Car(200));
        HttpEntity<CarSale> request = new HttpEntity<>(carSale);
        assertThrows(HttpClientErrorException.class, () -> restTemplate.postForEntity(HOST
                + CAR_SALE_ADD_URL, request, String.class));
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
        assertThrows(HttpClientErrorException.class, () -> restTemplate.exchange(HOST + CAR_SALE_UPDATE_URL,
                HttpMethod.PUT, request, CarSale.class));
    }

    @Test
    void updateNotExistCarSale() {
        CarSale carSale = restTemplate.getForObject(HOST + CAR_SALE_GET_URL + 3, CarSale.class);
        carSale.setCarSaleId(37);
        HttpEntity<CarSale> request = new HttpEntity<>(carSale);
        assertThrows(HttpClientErrorException.class, () -> restTemplate.exchange(HOST + CAR_SALE_UPDATE_URL,
                HttpMethod.PUT, request, CarSale.class));
    }

    @Test
    void deleteCarSale() {
        int carSaleId = 4;
        restTemplate.delete(HOST + CAR_SALE_DELETE_URL + carSaleId);
        assertThrows(HttpClientErrorException.NotFound.class, () -> restTemplate.getForEntity(HOST
                + CAR_SALE_DELETE_URL + carSaleId, CarSale.class));
    }

    @Test
    void deleteNotExistCarSale() {
        assertThrows(HttpClientErrorException.NotFound.class, () -> restTemplate.delete(HOST
                + CAR_SALE_DELETE_URL + 40));
    }
}
