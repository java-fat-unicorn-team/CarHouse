package com.carhouse.rest.conrtoller;

import com.carhouse.model.*;
import com.carhouse.service.CarService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {

    private static final String CARS_LIST_STORAGE_JSON = "car-list-storage.json";

    @Mock
    private CarService carService;

    @InjectMocks
    private CarController carController;

    private ObjectMapper objectMapper = new ObjectMapper();
    private List<Car> carList;
    private MockMvc mockMvc;

    @BeforeEach
    void setup() throws IOException {
        mockMvc = MockMvcBuilders.standaloneSetup(carController).build();
        File file = new ClassPathResource(CARS_LIST_STORAGE_JSON).getFile();
        carList = objectMapper.readValue(file, new TypeReference<List<Car>>(){});
    }

    @Test
    void getCars() throws Exception {
        when(carService.getCars()).thenReturn(carList);
        mockMvc.perform(get("/carSale/car"))
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
        mockMvc.perform(get("/carSale/car/{carId}", carId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(objectMapper.writeValueAsString(car)));
        verify(carService, times(1)).getCar(carId);
    }

    @Test
    void addCar() throws Exception {
        int carId = 1;
        Car car = carList.get(carId);
        when(carService.addCar(any(Car.class))).thenReturn(carId);
        mockMvc.perform(post("/carSale/car")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(objectMapper.writeValueAsString(car)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(carId)));
        verify(carService, times(1)).addCar(any(Car.class));
    }

    @Test
    void updateCar() throws Exception {
        int carId = 1;
        Car car = carList.get(carId);
        mockMvc.perform(put("/carSale/car")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(car)))
                .andExpect(status().isOk());
        verify(carService, times(1)).updateCar(any(Car.class));
    }

    @Test
    void deleteCar() throws Exception {
        int carId = 2;
        mockMvc.perform(delete("/carSale/car/{carId}", carId))
                .andExpect(status().isOk());
        verify(carService, times(1)).deleteCar(carId);
    }
}
