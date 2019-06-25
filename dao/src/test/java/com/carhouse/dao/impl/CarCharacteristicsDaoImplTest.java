package com.carhouse.dao.impl;

import com.carhouse.model.FuelType;
import com.carhouse.model.Transmission;
import com.carhouse.dao.CarCharacteristicsDao;
import com.carhouse.model.CarCharacteristics;
import com.carhouse.model.CarMake;
import com.carhouse.model.CarModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringTestConfiguration
class CarCharacteristicsDaoImplTest {

    CarCharacteristicsDao carCharacteristicsDao;

    @Autowired
    CarCharacteristicsDaoImplTest(CarCharacteristicsDao carCharacteristicsDao) {
        this.carCharacteristicsDao = carCharacteristicsDao;
    }

    @Test
    void getCarsCharacteristics() {
        assertEquals(5, carCharacteristicsDao.getCarsCharacteristics().size());
    }

    @Test
    void testGetNonExistentCarsCharacteristics() {
        assertThrows(EmptyResultDataAccessException.class, () -> carCharacteristicsDao.getCarCharacteristics(30));
    }

    @Test
    void getCarCharacteristics() {
        assertEquals(1, carCharacteristicsDao.getCarCharacteristics(1).getCarId());
        assertEquals(130300, carCharacteristicsDao.getCarCharacteristics(3).getMileage());
        assertEquals(3, carCharacteristicsDao.getCarCharacteristics(4).getCarModel().getCarModelId());
    }

    @Test
    void addCarCharacteristics() {
        int size = carCharacteristicsDao.getCarsCharacteristics().size();
        CarCharacteristics newCarCharacteristics = new CarCharacteristics(2, Date.valueOf("2016-03-02"),
                133455, new FuelType(2), new Transmission(1), new CarModel(3,
                new CarMake(2)));
        int index = carCharacteristicsDao.addCarCharacteristics(newCarCharacteristics);
        CarCharacteristics obtainedCarCharacteristics = carCharacteristicsDao.getCarCharacteristics(index);
        assertEquals(size + 1, carCharacteristicsDao.getCarsCharacteristics().size());
        assertEquals(newCarCharacteristics.getMileage(), obtainedCarCharacteristics.getMileage());
        assertEquals(newCarCharacteristics.getTransmission().getTransmissionId(),
                obtainedCarCharacteristics.getTransmission().getTransmissionId());
        assertEquals(newCarCharacteristics.getCarModel().getCarModelId(),
                obtainedCarCharacteristics.getCarModel().getCarModelId());

    }

    @Test
    void addCarCharacteristicsWithWrongReferences() {
        assertThrows(DataIntegrityViolationException.class,() -> carCharacteristicsDao.addCarCharacteristics(
                new CarCharacteristics(2, Date.valueOf("2018-04-01"), 33455, new FuelType(10),
                        new Transmission(20), new CarModel(15, new CarMake(21)))));
    }

    @Test
    void updateCarCharacteristics() {
        CarCharacteristics newCarCharacteristics = new CarCharacteristics(2, Date.valueOf("2017-04-01"),
                233455, new FuelType(2), new Transmission(2), new CarModel(1,
                new CarMake(2)));
        carCharacteristicsDao.updateCarCharacteristics(newCarCharacteristics);
        CarCharacteristics obtainedCarCharacteristics = carCharacteristicsDao.getCarCharacteristics(2);
        assertEquals(newCarCharacteristics.getMileage(), obtainedCarCharacteristics.getMileage());
        assertEquals(newCarCharacteristics.getTransmission().getTransmissionId(),
                obtainedCarCharacteristics.getTransmission().getTransmissionId());
        assertEquals(newCarCharacteristics.getCarModel().getCarModelId(),
                obtainedCarCharacteristics.getCarModel().getCarModelId());
    }

    @Test
    void updateCarCharacteristicsWithWrongReferences() {
        assertThrows(DataIntegrityViolationException.class,() -> carCharacteristicsDao.updateCarCharacteristics(
                new CarCharacteristics(2, Date.valueOf("2016-07-01"), 233455, new FuelType(10),
                        new Transmission(20), new CarModel(15, new CarMake(21)))));
    }

    @Test
    void deleteCarCharacteristics() {
        int size = carCharacteristicsDao.getCarsCharacteristics().size();
        carCharacteristicsDao.deleteCarCharacteristics(5);
        assertEquals(size - 1, carCharacteristicsDao.getCarsCharacteristics().size());
        assertThrows(EmptyResultDataAccessException.class, () -> carCharacteristicsDao.getCarCharacteristics(5));
    }

    @Test
    void deleteCarCharacteristicsWhichHaveReferences() {
        assertThrows(DataIntegrityViolationException.class,
                () -> carCharacteristicsDao.deleteCarCharacteristics(3));
    }
}