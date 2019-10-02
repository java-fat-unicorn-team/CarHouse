package com.carhouse.dao.impl;

import com.carhouse.dao.CarModelDao;
import com.carhouse.dao.config.TestConfiguration;
import com.carhouse.dao.config.TestSpringJDBCConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles(profiles ="h2-database")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class, TestSpringJDBCConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CarModelDaoImplTest {

    private CarModelDao carModelDao;

    @Autowired
    CarModelDaoImplTest(CarModelDao carModelDao) {
        this.carModelDao = carModelDao;
    }

    @Test
    void getCarModels() {
        assertEquals(2, carModelDao.getCarModels(2).size());
    }

    @Test
    void getCarModel() {
        assertEquals("M5", carModelDao.getCarModel(2).getCarModel());
        assertEquals(1, carModelDao.getCarModel(1).getCarMake().getCarMakeId());
        assertEquals("M8", carModelDao.getCarModel(3).getCarModel());
    }

    @Test
    void gerNonExistentCatModel() {
        assertThrows(EmptyResultDataAccessException.class, () -> carModelDao.getCarModel(8));
    }
}
