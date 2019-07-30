package com.carhouse.rest.controller;

import com.carhouse.model.Car;
import com.carhouse.rest.testConfig.RestTestConfig;
import com.carhouse.rest.handler.RestExceptionHandler;
import com.carhouse.service.CarService;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@ContextConfiguration(classes = RestTestConfig.class)
class CarControllerTest {

    private static final String CARS_LIST_STORAGE_JSON = "car-list-storage.json";
    private static final String CAR_LIST_GET_URL = "/carSale/car";
    private static final String CAR_GET_URL = "/carSale/car/{carId}";
    private static final String CAR_ADD_URL = "/carSale/car";
    private static final String CAR_UPDATE_URL = "/carSale/car";
    private static final String CAR_DELETE_URL = "/carSale/car/{carId}";

    @Mock
    private CarService carService;

    @InjectMocks
    private CarController carController;

    @Autowired
    private RestExceptionHandler restExceptionHandler;

    @Autowired
    private ObjectMapper objectMapper;
    private List<Car> carList;
    private MockMvc mockMvc;

    @BeforeEach
    void setup() throws IOException {
        mockMvc = MockMvcBuilders.standaloneSetup(carController)
                .setControllerAdvice(restExceptionHandler)
                .build();
        File file = new ClassPathResource(CARS_LIST_STORAGE_JSON).getFile();
        carList = objectMapper.readValue(file, new TypeReference<List<Car>>() {
        });
    }

    @Test
    void getCars() throws Exception {
        when(carService.getCars()).thenReturn(carList);
        mockMvc.perform(get(CAR_LIST_GET_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(objectMapper.writeValueAsString(carList)));
        verify(carService, times(1)).getCars();
    }

    @Test
    void getCar() throws Exception {
        int carId = 1;
        Car car = carList.get(carId);
        when(carService.getCar(carId)).thenReturn(car);
        mockMvc.perform(get(CAR_GET_URL, carId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(objectMapper.writeValueAsString(car)));
        verify(carService, times(1)).getCar(carId);
    }

    @Test
    void getNotExistCarSale() throws Exception {
        int carId = 21;
        when(carService.getCar(carId)).thenThrow(NotFoundException.class);
        mockMvc.perform(get(CAR_GET_URL, carId))
                .andExpect(status().isNotFound());
        verify(carService, times(1)).getCar(carId);
    }

    @Test
    void addCar() throws Exception {
        int carId = 1;
        Car car = carList.get(carId);
        when(carService.addCar(any(Car.class))).thenReturn(carId);
        mockMvc.perform(post(CAR_ADD_URL)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(car)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(carId)));
        verify(carService, times(1)).addCar(any(Car.class));
    }

    @Test
    void addCarWithWrongReference() throws Exception {
        int carId = 1;
        Car car = carList.get(carId);
        when(carService.addCar(any(Car.class))).thenThrow(WrongReferenceException.class);
        mockMvc.perform(post(CAR_ADD_URL)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(car)))
                .andExpect(status().isFailedDependency());
        verify(carService, times(1)).addCar(any(Car.class));
    }

    @Test
    void updateCar() throws Exception {
        int carId = 1;
        Car car = carList.get(carId);
        when(carService.updateCar(any(Car.class))).thenReturn(true);
        mockMvc.perform(put(CAR_UPDATE_URL)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(car)))
                .andExpect(status().isOk());
        verify(carService, times(1)).updateCar(any(Car.class));
    }

    @Test
    void updateCarWithWrongReference() throws Exception {
        int carId = 1;
        Car car = carList.get(carId);
        when(carService.updateCar(any(Car.class))).thenThrow(WrongReferenceException.class);
        mockMvc.perform(put(CAR_UPDATE_URL)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(car)))
                .andExpect(status().isFailedDependency());
        verify(carService, times(1)).updateCar(any(Car.class));
    }

    @Test
    void updateNotExistCar() throws Exception {
        int carId = 1;
        Car car = carList.get(carId);
        when(carService.updateCar(any(Car.class))).thenThrow(NotFoundException.class);
        mockMvc.perform(put(CAR_UPDATE_URL)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(car)))
                .andExpect(status().isNotFound());
        verify(carService, times(1)).updateCar(any(Car.class));
    }

    @Test
    void deleteCar() throws Exception {
        int carId = 2;
        when(carService.deleteCar(carId)).thenReturn(true);
        mockMvc.perform(delete(CAR_DELETE_URL, carId))
                .andExpect(status().isOk());
        verify(carService, times(1)).deleteCar(carId);
    }

    @Test
    void deleteNotExistCar() throws Exception {
        int carId = 2;
        when(carService.deleteCar(carId)).thenThrow(NotFoundException.class);
        mockMvc.perform(delete(CAR_DELETE_URL, carId))
                .andExpect(status().isNotFound());
        verify(carService, times(1)).deleteCar(carId);
    }
}
