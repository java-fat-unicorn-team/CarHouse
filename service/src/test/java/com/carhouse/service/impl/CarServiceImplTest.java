package com.carhouse.service.impl;

import com.carhouse.dao.CarDao;
import com.carhouse.model.Car;
import com.carhouse.service.exception.WrongReferenceException;
import javassist.NotFoundException;
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

import static org.junit.jupiter.api.Assertions.*;
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
    void getCar() throws NotFoundException {
        int carId = 1;
        when(carDao.getCar(carId)).thenReturn(listCars.get(carId));
        assertEquals(listCars.get(carId).getCarId(), carService.getCar(carId).getCarId());
        verify(carDao, times(1)).getCar(carId);
    }

    @Test
    void getNotExistCar() {
        int carId = 10;
        when(carDao.getCar(carId)).thenThrow(EmptyResultDataAccessException.class);
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> carService.getCar(carId));
        assertTrue(thrown.getMessage().contains("there is not such car"));
    }

    @Test
    void addCar() {
        Integer newCarId = 5;
        Car car = new Car(3);
        when(carDao.addCar(car)).thenReturn(newCarId);
        assertEquals(newCarId, carService.addCar(car));
        verify(carDao, times(1)).addCar(car);
    }

    @Test
    void addCarWithWrongReference() {
        Car car = new Car(13);
        when(carDao.addCar(car)).thenThrow(DataIntegrityViolationException.class);
        WrongReferenceException thrown = assertThrows(WrongReferenceException.class, () -> carService.addCar(car));
        assertTrue(thrown.getMessage().contains("there is wrong references in your car"));
    }

    @Test
    void updateCar() throws NotFoundException {
        Car car = new Car(5);
        when(carDao.updateCar(car)).thenReturn(true);
        boolean isUpdated = carService.updateCar(car);
        assertTrue(isUpdated);
        verify(carDao, times(1)).updateCar(car);
    }

    @Test
    void updateCarWithWrongReference() {
        Car car = new Car(5);
        when(carDao.updateCar(car)).thenThrow(DataIntegrityViolationException.class);
        WrongReferenceException thrown = assertThrows(WrongReferenceException.class, () -> carService.updateCar(car));
        assertTrue(thrown.getMessage().contains("there is wrong references in your car"));
    }

    @Test
    void updateNotExistCar() {
        Car car = new Car(5);
        when(carDao.getCar(car.getCarId())).thenThrow(EmptyResultDataAccessException.class);
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> carService.updateCar(car));
        assertTrue(thrown.getMessage().contains("there is not such car"));
    }

    @Test
    void deleteCar() throws NotFoundException {
        int carId = 2;
        when(carDao.deleteCar(carId)).thenReturn(true);
        boolean isDeleted = carService.deleteCar(carId);
        assertTrue(isDeleted);
        verify(carDao, times(1)).deleteCar(carId);
    }

    @Test
    void deleteNotExistCar() {
        int carId = 12;
        when(carDao.deleteCar(carId)).thenReturn(false);
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> carService.deleteCar(carId));
        assertTrue(thrown.getMessage().contains("car with id = " + carId + " you are trying to delete does not exist"));
    }
}
