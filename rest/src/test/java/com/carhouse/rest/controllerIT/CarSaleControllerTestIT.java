package com.carhouse.rest.controllerIT;

import com.carhouse.model.CarSale;
import com.carhouse.rest.response.ExceptionJSONResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CarSaleControllerTestIT {

    private static final String HOST = "http://localhost:8086";
    private static final String CAR_SALE_LIST_GET_URL = "/carSale";
    private static final String CAR_SALE_GET_URL = "/carSale/";
    private static final String CAR_SALE_ADD_URL = "/carSale";
    private static final String CAR_SALE_UPDATE_URL = "/carSale/{carSaleId}";
    private static final String CAR_SALE_DELETE_URL = "/carSale/";

    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();
    private MultipartFile file = new MockMultipartFile("file", "test.txt", "image/*",
            new byte[]{});

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
    void getNotExistCarSale() throws IOException {
        int carSaleId = 150;
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> restTemplate.getForEntity(HOST + CAR_SALE_GET_URL + carSaleId, String.class));
        ExceptionJSONResponse response = objectMapper.readValue(exception.getResponseBodyAsString(),
                ExceptionJSONResponse.class);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        assertEquals("there is not car sale with id = " + carSaleId, response.getMessages().get(0));
        assertEquals(CAR_SALE_GET_URL + carSaleId, response.getPath());
    }

    @Test
    void getCarSaleValidationError() throws IOException {
        int carSaleId = -150;
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> restTemplate.getForEntity(HOST + CAR_SALE_GET_URL + carSaleId, String.class));
        ExceptionJSONResponse response = objectMapper.readValue(exception.getResponseBodyAsString(),
                ExceptionJSONResponse.class);
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertEquals("car sale id can't be negative", response.getMessages().get(0));
        assertEquals(CAR_SALE_GET_URL + carSaleId, response.getPath());
    }

    @Test
    void addCarSale() {
        CarSale carSale = restTemplate.getForObject(HOST + CAR_SALE_GET_URL + 3, CarSale.class);
        carSale.setPrice(new BigDecimal(23300));
        carSale.setDate(new Date());
        ResponseEntity<Integer> response = restTemplate.postForEntity(HOST + CAR_SALE_ADD_URL,
                createMultipartRequest(carSale, file), Integer.class);
        assertEquals(200, response.getStatusCodeValue());
        Integer id = response.getBody();
        assertTrue(id > 0);
    }

    @Test
    void addCarSaleWithWrongReference() throws IOException {
        CarSale carSale = restTemplate.getForObject(HOST + CAR_SALE_GET_URL + 3, CarSale.class);
        carSale.getUser().setUserId(200);
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> restTemplate.postForEntity(HOST + CAR_SALE_ADD_URL, createMultipartRequest(carSale, file),
                        Integer.class));
        ExceptionJSONResponse response = objectMapper.readValue(exception.getResponseBodyAsString(),
                ExceptionJSONResponse.class);
        assertEquals(HttpStatus.FAILED_DEPENDENCY.value(), response.getStatus());
        assertEquals("there is wrong references in your car sale", response.getMessages().get(0));
        assertEquals(CAR_SALE_ADD_URL, response.getPath());
    }

    @Test
    void addCarSaleValidationError() throws IOException {
        CarSale carSale = restTemplate.getForObject(HOST + CAR_SALE_GET_URL + 3, CarSale.class);
        carSale.setPrice(new BigDecimal(-123));
        carSale.getCar().setCarId(-22).setFuelType(null);
        HttpEntity<CarSale> request = new HttpEntity<>(carSale);
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> restTemplate.postForEntity(HOST + CAR_SALE_ADD_URL, createMultipartRequest(carSale, file),
                        Integer.class));
        ExceptionJSONResponse response = objectMapper.readValue(exception.getResponseBodyAsString(),
                ExceptionJSONResponse.class);
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertFalse(response.getMessages().isEmpty());
    }

    @Test
    void updateCarSale() {
        int carSaleId = 3;
        int newCarModelId = 2;
        BigDecimal newCarPrice = new BigDecimal(23300);
        CarSale carSale = restTemplate.getForObject(HOST + CAR_SALE_GET_URL + carSaleId, CarSale.class);
        carSale.setPrice(newCarPrice);
        carSale.getCar().getCarModel().setCarModelId(newCarModelId);
        ResponseEntity response = restTemplate.postForEntity(HOST + CAR_SALE_UPDATE_URL,
                createMultipartRequest(carSale, file), String.class, carSaleId);
        assertEquals(200, response.getStatusCodeValue());
        CarSale updatedCarSale = restTemplate.getForObject(HOST + CAR_SALE_GET_URL + carSaleId, CarSale.class);
        assertEquals(newCarPrice, updatedCarSale.getPrice());
        assertEquals(newCarModelId, updatedCarSale.getCar().getCarModel().getCarModelId());
    }

    @Test
    void updateCarSaleWithWrongReference() {
        int carSaleId = 3;
        CarSale carSale = restTemplate.getForObject(HOST + CAR_SALE_GET_URL + carSaleId, CarSale.class);
        carSale.getUser().setUserId(200);
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> restTemplate.postForEntity(HOST + CAR_SALE_UPDATE_URL,
                        createMultipartRequest(carSale, file), String.class, carSaleId));
        assertEquals(HttpStatus.FAILED_DEPENDENCY, exception.getStatusCode());
        assertTrue(exception.getResponseBodyAsString().contains("there is wrong references in your car sale"));
    }

    @Test
    void updateNotExistCarSale() {
        int carSaleId = 37;
        CarSale carSale = restTemplate.getForObject(HOST + CAR_SALE_GET_URL + 3, CarSale.class);
        carSale.setCarSaleId(carSaleId);
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> restTemplate.postForEntity(HOST + CAR_SALE_UPDATE_URL,
                        createMultipartRequest(carSale, file), String.class, carSaleId));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertTrue(exception.getResponseBodyAsString().contains("there is not car sale with id = " + carSaleId));
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

    @Test
    void deleteCarSaleValidationError() throws IOException {
        int carSaleId = -4;
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> restTemplate.exchange(HOST + CAR_SALE_DELETE_URL + carSaleId,
                        HttpMethod.DELETE, null, String.class));
        ExceptionJSONResponse response = objectMapper.readValue(exception.getResponseBodyAsString(),
                ExceptionJSONResponse.class);
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertEquals("car sale id can't be negative", response.getMessages().get(0));
        assertEquals(CAR_SALE_DELETE_URL + carSaleId, response.getPath());
    }

    private HttpEntity<MultiValueMap<String, Object>> createMultipartRequest(final CarSale carSale,
                                                                             final MultipartFile file) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("carSale", carSale);
        body.add("file", file.getResource());
        return new HttpEntity<>(body, headers);
    }
}
