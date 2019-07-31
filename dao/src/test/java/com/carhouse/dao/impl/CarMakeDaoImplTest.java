package com.carhouse.dao.impl;

import com.carhouse.dao.CarMakeDao;
import com.carhouse.dao.config.TestConfig;
import database.test.config.TestSpringJDBCConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class, TestSpringJDBCConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CarMakeDaoImplTest {

    private CarMakeDao carMakeDao;

    @Autowired
    CarMakeDaoImplTest(CarMakeDao carMakeDao) {
        this.carMakeDao = carMakeDao;
    }

    @Test
    void getCarMakes() {
        assertEquals(2, carMakeDao.getCarMakes().size());
    }

    @Test
    void getCarMake() {
        assertEquals("Mercedes", carMakeDao.getCarMake(1).getCarMake());
        assertEquals("BMW", carMakeDao.getCarMake(2).getCarMake());
    }

    @Test
    void getNonExistentCarMake() {
        assertThrows(EmptyResultDataAccessException.class,
                () -> carMakeDao.getCarMake(5));
    }
}
