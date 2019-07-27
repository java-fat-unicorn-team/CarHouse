package com.carhouse.dao.impl;

import com.carhouse.dao.CarDao;
import com.carhouse.dao.CarHasCarFeatureDao;
import com.carhouse.rest.config.TestConfig;
import com.carhouse.rest.config.TestSpringJDBCConfig;
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
        int carId = 2;
        int carFeatureId = 1;
        int size = carDao.getCar(carId).getCarFeatureList().size();
        carHasCarFeatureDao.addCarFeature(carId, carFeatureId);
        assertEquals(size + 1, carDao.getCar(carId).getCarFeatureList().size());
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
        int carId = 2;
        int carFeatureId = 3;
        int size = carDao.getCar(carId).getCarFeatureList().size();
        carHasCarFeatureDao.deleteCarFeature(carId, carFeatureId);
        assertEquals(size - 1, carDao.getCar(carId).getCarFeatureList().size());
    }

    @Test
    void deleteCarFeatureListFromCar() {
        int carId = 2;
        carHasCarFeatureDao.deleteAllCarFeatures(carId);
        assertEquals(0, carDao.getCar(carId).getCarFeatureList().size());
    }
}