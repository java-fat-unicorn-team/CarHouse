package com.carhouse.rest.controllerIT;

import com.carhouse.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class CarControllerTestIT {

    private static final String HOST = "http://localhost:8086";
    private static final String CAR_LIST_GET_URL = "/carSale/car";
    private static final String CAR_GET_URL = "/carSale/car/";
    private static final String CAR_ADD_URL = "/carSale/car/";
    private static final String CAR_UPDATE_URL = "/carSale/car/";
    private static final String CAR_DELETE_URL = "/carSale/car/";

    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getCars() {
        ResponseEntity<String> response = restTemplate.getForEntity(HOST + CAR_LIST_GET_URL, String.class);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void getCar() {
        ResponseEntity<Car> response = restTemplate.getForEntity(HOST + CAR_GET_URL + 1, Car.class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void getNotExistCarSale() {
        int carSaleId = 150;
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> restTemplate.getForEntity(HOST + CAR_GET_URL + carSaleId, String.class));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertTrue(exception.getResponseBodyAsString().contains("there is not car with id = " + carSaleId));
    }

    @Test
    void addCar() {
        Car car = new Car(2);
        car.setYear(Date.valueOf("2017-04-05"));
        car.setCarModel(new CarModel(2, new CarMake(2)));
        car.setFuelType(new FuelType(1));
        car.setTransmission(new Transmission(2));
        HttpEntity<Car> request = new HttpEntity<>(car);
        ResponseEntity<String> response = restTemplate.postForEntity(HOST + CAR_ADD_URL, request, String.class);
        assertEquals(200, response.getStatusCodeValue());
        Integer id = Integer.valueOf(response.getBody());
        assertTrue(id > 0);
    }

    @Test
    void addCarWithWrongReference() {
        Car car = new Car(2);
        car.setYear(Date.valueOf("2017-04-05"));
        car.setCarModel(new CarModel(20, new CarMake(12)));
        car.setFuelType(new FuelType(15));
        car.setTransmission(new Transmission(32));
        HttpEntity<Car> request = new HttpEntity<>(car);
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> restTemplate.postForEntity(HOST + CAR_ADD_URL, request, String.class));
        assertEquals(HttpStatus.FAILED_DEPENDENCY, exception.getStatusCode());
        assertTrue(exception.getResponseBodyAsString().contains("there is wrong references in your car"));
    }

    @Test
    void updateCar() {
        int carId = 2;
        int mileage = 1234567;
        Car car = restTemplate.getForObject(HOST + CAR_GET_URL + carId, Car.class);
        car.setMileage(mileage);
        HttpEntity<Car> request = new HttpEntity<>(car);
        ResponseEntity response = restTemplate.exchange(HOST + CAR_UPDATE_URL, HttpMethod.PUT, request, Car.class);
        assertEquals(200, response.getStatusCodeValue());
        Car updatedCar = restTemplate.getForObject(HOST + CAR_GET_URL + carId, Car.class);
        assertEquals(mileage, updatedCar.getMileage());
    }

    @Test
    void updateCarWithWrongReference() {
        int carId = 4;
        Car car = restTemplate.getForObject(HOST + CAR_GET_URL + carId, Car.class);
        car.setFuelType(new FuelType(15));
        car.setTransmission(new Transmission(32));
        HttpEntity<Car> request = new HttpEntity<>(car);
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> restTemplate.exchange(HOST + CAR_UPDATE_URL, HttpMethod.PUT, request, String.class));
        assertEquals(HttpStatus.FAILED_DEPENDENCY, exception.getStatusCode());
        assertTrue(exception.getResponseBodyAsString().contains("there is wrong references in your car"));
    }

    @Test
    void updateNotExistCar() {
        int carId = 1;
        int carSaleId = 120;
        Car car = restTemplate.getForObject(HOST + CAR_GET_URL + carId, Car.class);
        car.setCarId(carSaleId);
        HttpEntity<Car> request = new HttpEntity<>(car);
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> restTemplate.exchange(HOST + CAR_UPDATE_URL, HttpMethod.PUT, request, String.class));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertTrue(exception.getResponseBodyAsString().contains("there is not car with id = " + carSaleId));
    }

    @Test
    void deleteCar() {
        Car car = new Car(2);
        car.setYear(Date.valueOf("2017-04-05"));
        car.setCarModel(new CarModel(2, new CarMake(2)));
        car.setFuelType(new FuelType(1));
        car.setTransmission(new Transmission(2));
        HttpEntity<Car> request = new HttpEntity<>(car);
        ResponseEntity<String> addCarResponse = restTemplate.postForEntity(HOST + CAR_ADD_URL, request, String.class);
        Integer carId = Integer.valueOf(addCarResponse.getBody());
        ResponseEntity response = restTemplate.exchange(HOST + CAR_DELETE_URL + carId,
                HttpMethod.DELETE, null, String.class);
        assertEquals(200, response.getStatusCodeValue());
        assertThrows(HttpClientErrorException.NotFound.class, () -> restTemplate.getForEntity(HOST
                + CAR_GET_URL + carId, Car.class));
    }

    @Test
    void deleteCarWhichHasReferences() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> restTemplate.exchange(HOST + CAR_DELETE_URL + 1,
                        HttpMethod.DELETE, null, String.class));
        assertEquals(HttpStatus.FAILED_DEPENDENCY, exception.getStatusCode());
        assertTrue(exception.getResponseBodyAsString().contains("car with id = " + 1 + " has references"));
        assertNotNull(restTemplate.getForEntity(HOST + CAR_GET_URL + 1, Car.class));
    }

    @Test
    void deleteNotExistCar() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> restTemplate.exchange(HOST + CAR_DELETE_URL + 40,
                        HttpMethod.DELETE, null, String.class));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertTrue(exception.getResponseBodyAsString().contains("there is not car with id = " + 40 + " to delete"));
    }
}
