package com.carhouse.service.impl;

import com.carhouse.dao.CarDao;
import com.carhouse.model.Car;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    private CarDao carDao;

    @InjectMocks
    private CarServiceImpl carService;

    private static List<Car> listCars;

    @BeforeAll
    static void addCars() {
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
        int carId = 1;
        when(carDao.getCar(carId)).thenReturn(listCars.get(carId));
        assertEquals(listCars.get(carId).getCarId(), carService.getCar(carId).getCarId());
        verify(carDao, times(1)).getCar(carId);
    }

    @Test
    void getNotExistCar() {
        int carId = 10;
        when(carDao.getCar(carId)).thenThrow(EmptyResultDataAccessException.class);
        assertThrows(EmptyResultDataAccessException.class, () -> carService.getCar(carId));
    }

    @Test
    void addCar() {
        Car car = new Car(3);
        carService.addCar(car);
        verify(carDao, times(1)).addCar(car);
    }

    @Test
    void addCarWithWrongReference() {
        Car car = new Car(13);
        when(carDao.addCar(car)).thenThrow(DataIntegrityViolationException.class);
        assertThrows(DataIntegrityViolationException.class, () -> carService.addCar(car));
    }

    @Test
    void updateCar() {
        Car car = new Car(5);
        carService.updateCar(car);
        verify(carDao, times(1)).updateCar(car);
    }

    @Test
    void deleteCar() {
        int carId = 2;
        carService.deleteCar(carId);
        verify(carDao, times(1)).deleteCar(carId);
    }
}
