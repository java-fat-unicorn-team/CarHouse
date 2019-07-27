package com.carhouse.dao.impl;

import com.carhouse.dao.CarFeatureDao;
import com.carhouse.rest.config.TestConfig;
import com.carhouse.rest.config.TestSpringJDBCConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class, TestSpringJDBCConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CarFeatureDaoImplTest {

    private CarFeatureDao carFeatureDao;

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

    @Test
    void getAllFeatures() {
        assertEquals(4, carFeatureDao.getAllFeatures().size());
    }
}