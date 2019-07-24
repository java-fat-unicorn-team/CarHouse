package com.carhouse.dao.impl;

import com.carhouse.dao.FuelTypeDao;
import config.TestConfig;
import config.TestSpringJDBCConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class, TestSpringJDBCConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class FuelTypeDaoImplTest {

    private FuelTypeDao fuelTypeDao;

    @Autowired
    FuelTypeDaoImplTest(FuelTypeDao fuelTypeDao) {
        this.fuelTypeDao = fuelTypeDao;
    }

    @Test
    void getFuelTypes() {
        assertEquals(4, fuelTypeDao.getFuelTypes().size());
    }
}
