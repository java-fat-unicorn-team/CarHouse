package com.carhouse.dao.impl;

import com.carhouse.dao.CarDao;
import com.carhouse.dao.CarHasCarFeatureDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.*;

@SpringTestConfiguration
class CarHasCarFeatureDaoImplTest {

    final CarHasCarFeatureDao carHasCarFeatureDao;
    final CarDao carDao;

    @Autowired
    public CarHasCarFeatureDaoImplTest(CarHasCarFeatureDao carHasCarFeatureDao, CarDao carDao) {
        this.carHasCarFeatureDao = carHasCarFeatureDao;
        this.carDao = carDao;
    }

    @Test
    void addCarFeatureToCar() {
        int size = carDao.getCar(2).getCarFeatureList().size();
        carHasCarFeatureDao.addCarFeatureToCar(2, 1);
        assertEquals(size + 1, carDao.getCar(2).getCarFeatureList().size());
    }

    @Test
    void addCarFeatureToNonExistentCar() {
        assertThrows(DataIntegrityViolationException.class, () ->
                carHasCarFeatureDao.addCarFeatureToCar(8, 1));
    }

    @Test
    void addNonExistentCarFeatureToCar() {
        assertThrows(DataIntegrityViolationException.class, () ->
                carHasCarFeatureDao.addCarFeatureToCar(3, 9));
    }

    @Test
    void addCarFeatureToCarWhatAlreadyExist() {
        assertThrows(DataIntegrityViolationException.class, () ->
                carHasCarFeatureDao.addCarFeatureToCar(2, 3));
    }

    @Test
    void deleteCarFeatureFromCar() {
        int size = carDao.getCar(2).getCarFeatureList().size();
        carHasCarFeatureDao.deleteCarFeatureFromCar(2, 3);
        assertEquals(size - 1, carDao.getCar(2).getCarFeatureList().size());
    }

    @Test
    void deleteCarFeatureListFromCar() {
        carHasCarFeatureDao.deleteCarFeatureListFromCar(2);
        assertEquals(0, carDao.getCar(2).getCarFeatureList().size());
    }
}