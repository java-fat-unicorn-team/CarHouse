package com.carhouse.dao.impl;

import com.carhouse.dao.CarFeatureDao;
import com.carhouse.dao.config.TestConfig;
import com.carhouse.dao.config.TestSpringJDBCConfig;
import com.carhouse.model.CarFeature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

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
        List<CarFeature> carFeatureList = carFeatureDao.getAllFeatures();
        assertEquals(4, carFeatureList.size());
        assertEquals("air conditioning", carFeatureList.get(1).getCarFeature());
    }
}