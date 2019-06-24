package com.spring.dao.impl;

import com.spring.dao.FuelTypeDao;
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

    @Test
    void getFuelType() {
        Assertions.assertEquals("Diesel", fuelTypeDao.getFuelType(2).getFuelType());
        Assertions.assertEquals("Electric", fuelTypeDao.getFuelType(4).getFuelType());
    }

    @Test
    void getNonExistentFuelType() {
        assertThrows(EmptyResultDataAccessException.class, () -> fuelTypeDao.getFuelType(5));
    }

    @Test
    void addFuelType() {
        int size = fuelTypeDao.getFuelTypes().size();
        int index = fuelTypeDao.addFuelType("Water");
        assertEquals(size + 1, fuelTypeDao.getFuelTypes().size());
        Assertions.assertEquals("Water", fuelTypeDao.getFuelType(index).getFuelType());
    }

    @Test
    void deleteFuelType() {
        int size = fuelTypeDao.getFuelTypes().size();
        fuelTypeDao.deleteFuelType(3);
        assertEquals(size - 1, fuelTypeDao.getFuelTypes().size());
        assertThrows(EmptyResultDataAccessException.class, () -> fuelTypeDao.getFuelType(3));
    }

    @Test
    void deleteFuelTypeWhichHaveReferences() {
        assertThrows(DataIntegrityViolationException.class, () -> fuelTypeDao.deleteFuelType(1));
    }

    @Test
    void updateFuelType() {
        fuelTypeDao.updateFuelType(2, "Wood");
        Assertions.assertEquals("Wood", fuelTypeDao.getFuelType(2).getFuelType());
    }
}
