package com.carhouse.rest.conrtoller;

import com.carhouse.model.*;
import com.carhouse.rest.JsonConverter;
import com.carhouse.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {

    @Mock
    private CarService carService;

    @InjectMocks
    private CarController carController;

    private JsonConverter jsonConverter = new JsonConverter();
    private List<Car> carList;
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(carController).build();
        Car newCar = new Car(2, Date.valueOf("2016-03-02"),
                133455, new FuelType(2), new Transmission(1), new CarModel(3,
                new CarMake(2)), new ArrayList<>() {{
            add(new CarFeature(2, ""));
            add(new CarFeature(1, ""));
        }});
        carList = new ArrayList<>() {{
            add(new Car(1));
            add(newCar);
            add(new Car(3));
        }};
    }

    @Test
    void getCars() throws Exception {
        when(carService.getCars()).thenReturn(carList);
        mockMvc.perform(get("/carSale/car"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(jsonConverter.asJsonString(carList)));
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
                .andExpect(content().json(jsonConverter.asJsonString(car)));
        verify(carService, times(1)).getCar(carId);
    }

    @Test
    void addCar() throws Exception {
        int carId = 1;
        Car car = carList.get(carId);
        when(carService.addCar(any(Car.class))).thenReturn(carId);
        mockMvc.perform(post("/carSale/car")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(jsonConverter.asJsonString(car)))
                .andExpect(status().isCreated())
                .andExpect(content().json(jsonConverter.asJsonString(carId)));
        verify(carService, times(1)).addCar(any(Car.class));
    }

    @Test
    void updateCar() throws Exception {
        int carId = 1;
        Car car = carList.get(carId);
        mockMvc.perform(put("/carSale/car")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(jsonConverter.asJsonString(car)))
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
