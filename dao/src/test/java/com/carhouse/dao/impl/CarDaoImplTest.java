package com.carhouse.dao.impl;

import com.carhouse.dao.CarDao;
import com.carhouse.model.*;
import com.carhouse.model.Car;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringTestConfiguration
class CarDaoImplTest {

    CarDao carDao;

    @Autowired
    CarDaoImplTest(CarDao carDao) {
        this.carDao = carDao;
    }

    @Test
    void getCarsCharacteristics() {
        assertEquals(5, carDao.getCars().size());
    }

    @Test
    void testGetNonExistentCarsCharacteristics() {
        assertThrows(EmptyResultDataAccessException.class, () -> carDao.getCar(30));
    }

    @Test
    void getCarCharacteristics() {
        assertEquals(1, carDao.getCar(1).getCarId());
        assertEquals(130300, carDao.getCar(3).getMileage());
        assertEquals(3, carDao.getCar(4).getCarModel().getCarModelId());
    }

    @Test
    void addCarCharacteristics() {
        int size = carDao.getCars().size();
        Car newCar = new Car(2, Date.valueOf("2016-03-02"),
                133455, new FuelType(2), new Transmission(1), new CarModel(3,
                new CarMake(2)));
        int index = carDao.addCar(newCar);
        Car obtainedCar = carDao.getCar(index);
        assertEquals(size + 1, carDao.getCars().size());
        assertEquals(newCar.getMileage(), obtainedCar.getMileage());
        assertEquals(newCar.getTransmission().getTransmissionId(),
                obtainedCar.getTransmission().getTransmissionId());
        assertEquals(newCar.getCarModel().getCarModelId(),
                obtainedCar.getCarModel().getCarModelId());

    }

    @Test
    void addCarCharacteristicsWithWrongReferences() {
        assertThrows(DataIntegrityViolationException.class,() -> carDao.addCar(
                new Car(2, Date.valueOf("2018-04-01"), 33455, new FuelType(10),
                        new Transmission(20), new CarModel(15, new CarMake(21)))));
    }

    @Test
    void updateCarCharacteristics() {
        Car newCar = new Car(2, Date.valueOf("2017-04-01"),
                233455, new FuelType(2), new Transmission(2), new CarModel(1,
                new CarMake(2)));
        carDao.updateCar(newCar);
        Car obtainedCar = carDao.getCar(2);
        assertEquals(newCar.getMileage(), obtainedCar.getMileage());
        assertEquals(newCar.getTransmission().getTransmissionId(),
                obtainedCar.getTransmission().getTransmissionId());
        assertEquals(newCar.getCarModel().getCarModelId(),
                obtainedCar.getCarModel().getCarModelId());
    }

    @Test
    void updateCarCharacteristicsWithWrongReferences() {
        assertThrows(DataIntegrityViolationException.class,() -> carDao.updateCar(
                new Car(2, Date.valueOf("2016-07-01"), 233455, new FuelType(10),
                        new Transmission(20), new CarModel(15, new CarMake(21)))));
    }

    @Test
    void deleteCarCharacteristics() {
        int size = carDao.getCars().size();
        carDao.deleteCar(5);
        assertEquals(size - 1, carDao.getCars().size());
        assertThrows(EmptyResultDataAccessException.class, () -> carDao.getCar(5));
    }

    @Test
    void deleteCarCharacteristicsWhichHaveReferences() {
        assertThrows(DataIntegrityViolationException.class,
                () -> carDao.deleteCar(3));
    }
}