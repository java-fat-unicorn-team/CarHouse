package com.carhouse.dao.impl;

import com.carhouse.dao.CarDao;
import com.carhouse.dao.CarHasCarFeatureDao;
import config.TestConfig;
import config.TestSpringJDBCConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class, TestSpringJDBCConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CarHasCarFeatureDaoImplTest {

    private CarHasCarFeatureDao carHasCarFeatureDao;
    private CarDao carDao;

    @Autowired
    public CarHasCarFeatureDaoImplTest(CarHasCarFeatureDao carHasCarFeatureDao, CarDao carDao) {
        this.carHasCarFeatureDao = carHasCarFeatureDao;
        this.carDao = carDao;
    }

    @Test
    void addCarFeatureToCar() {
        int size = carDao.getCar(2).getCarFeatureList().size();
        carHasCarFeatureDao.addCarFeature(2, 1);
        assertEquals(size + 1, carDao.getCar(2).getCarFeatureList().size());
    }

    @Test
    void addCarFeatureToNonExistentCar() {
        assertThrows(DataIntegrityViolationException.class, () ->
                carHasCarFeatureDao.addCarFeature(8, 1));
    }

    @Test
    void addNonExistentCarFeatureToCar() {
        assertThrows(DataIntegrityViolationException.class, () ->
                carHasCarFeatureDao.addCarFeature(3, 9));
    }

    @Test
    void addCarFeatureToCarWhatAlreadyExist() {
        assertThrows(DataIntegrityViolationException.class, () ->
                carHasCarFeatureDao.addCarFeature(2, 3));
    }

    @Test
    void deleteCarFeatureFromCar() {
        int size = carDao.getCar(2).getCarFeatureList().size();
        carHasCarFeatureDao.deleteCarFeature(2, 3);
        assertEquals(size - 1, carDao.getCar(2).getCarFeatureList().size());
    }

    @Test
    void deleteCarFeatureListFromCar() {
        carHasCarFeatureDao.deleteAllCarFeatures(2);
        assertEquals(0, carDao.getCar(2).getCarFeatureList().size());
    }
}