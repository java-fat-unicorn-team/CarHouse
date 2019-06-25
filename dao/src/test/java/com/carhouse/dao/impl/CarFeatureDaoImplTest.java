package com.carhouse.dao.impl;

import com.carhouse.dao.CarFeatureDao;
import com.carhouse.model.CarFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.junit.jupiter.api.Assertions.*;

@SpringTestConfiguration
class CarFeatureDaoImplTest {

    final CarFeatureDao carFeatureDao;

    @Autowired
    CarFeatureDaoImplTest(CarFeatureDao carFeatureDao) {
        this.carFeatureDao = carFeatureDao;
    }

    @Test
    void getCarFeatures() {
        assertEquals(3, carFeatureDao.getCarFeatures(1).size());
    }

    @Test
    void getNonExistentCarFeatures() {
        assertEquals(0, carFeatureDao.getCarFeatures(10).size());
    }

    @Test
    void getCarFeature() {
        assertEquals("Winter tires", carFeatureDao.getCarFeature(1).getCarFeature());
        assertEquals(2, carFeatureDao.getCarFeature(3).getCarCharacteristics().getCarId());
        assertEquals("Csenon", carFeatureDao.getCarFeature(4).getCarFeature());
    }

    @Test
    void getNonExistentCarFeature() {
        assertThrows(EmptyResultDataAccessException.class, () -> carFeatureDao.getCarFeature(12));
    }

    @Test
    void addCarFeature() {
        int size = carFeatureDao.getCarFeatures(2).size();
        int index = carFeatureDao.addCarFeature("Chip", 2);
        CarFeature obtainedCarFeature = carFeatureDao.getCarFeature(index);
        assertEquals(size + 1, carFeatureDao.getCarFeatures(2).size());
        assertEquals("Chip", obtainedCarFeature.getCarFeature());
        assertEquals(2, obtainedCarFeature.getCarCharacteristics().getCarId());
    }

    @Test
    void addCarFeatureForNonExistentCar() {
        assertThrows(DataIntegrityViolationException.class,
                () -> carFeatureDao.addCarFeature("Chip", 10));
    }

    @Test
    void updateCarFeature() {
        int carCharacteristicsId = carFeatureDao.getCarFeature(3).getCarCharacteristics().getCarId();
        carFeatureDao.updateCarFeature("Air suspension", 3);
        CarFeature obtainedCarFeature = carFeatureDao.getCarFeature(3);
        assertEquals("Air suspension", obtainedCarFeature.getCarFeature());
        assertEquals(carCharacteristicsId, obtainedCarFeature.getCarCharacteristics().getCarId());
    }

    @Test
    void deleteCarFeature() {
        int size = carFeatureDao.getCarFeatures(1).size();
        carFeatureDao.deleteCarFeature(2);
        assertEquals(size - 1, carFeatureDao.getCarFeatures(1).size());
    }
}