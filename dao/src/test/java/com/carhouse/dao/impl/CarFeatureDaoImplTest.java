package com.carhouse.dao.impl;

import com.carhouse.dao.CarFeatureDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringTestConfiguration
class CarFeatureDaoImplTest {

    final CarFeatureDao carFeatureDao;

    @Autowired
    CarFeatureDaoImplTest(CarFeatureDao carFeatureDao) {
        this.carFeatureDao = carFeatureDao;
    }

    @Test
    void getCarFeatures() {
        assertEquals(2, carFeatureDao.getCarFeatures(1).size());
    }

    @Test
    void getNonExistentCarFeatures() {
        assertEquals(0, carFeatureDao.getCarFeatures(10).size());
    }
}