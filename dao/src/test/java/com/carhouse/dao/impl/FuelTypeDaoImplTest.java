package com.carhouse.dao.impl;

import com.carhouse.dao.FuelTypeDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.junit.jupiter.api.Assertions.*;

@SpringTestConfiguration
class FuelTypeDaoImplTest {

    FuelTypeDao fuelTypeDao;

    @Autowired
    FuelTypeDaoImplTest(FuelTypeDao fuelTypeDao) {
        this.fuelTypeDao = fuelTypeDao;
    }

    @Test
    void getFuelTypes() {
        assertEquals(4, fuelTypeDao.getFuelTypes().size());
    }
}
