package com.carhouse.service.impl;

import com.carhouse.dao.CarDao;
import com.carhouse.model.*;
import com.carhouse.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    private CarDao carDao;

    @InjectMocks
    private CarServiceImpl carService;

    private List<Car> listCars;

    @BeforeEach
    void addCars() {
        listCars = new ArrayList<>() {{
           add(new Car(1));
           add(new Car(2));
           add(new Car(3));
        }};
    }

    @Test
    void getCars() {
        when(carDao.getCars()).thenReturn(listCars);
        assertEquals(listCars.size(), carService.getCars().size());
    }

    @Test
    void getCar() {
        int id = 1;
        when(carDao.getCar(id)).thenReturn(listCars.get(id));
        assertEquals(listCars.get(id).getCarId(), carService.getCar(id).getCarId());
        verify(carDao, times(1)).getCar(id);
    }

    @Test
    void addCar() {
        Car car = new Car(3);
        carService.addCar(car);
        verify(carDao, times(1)).addCar(car);
    }

    @Test
    void updateCar() {
        Car car = new Car(5);
        carService.updateCar(car);
        verify(carDao, times(1)).updateCar(car);
    }

    @Test
    void deleteCar() {
        carService.deleteCar(2);
        verify(carDao, times(1)).deleteCar(2);
    }
}
