package com.spring.rest.dao.impl;

import com.spring.rest.dao.CarMakeDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringTestConfiguration
class CarMakeDaoImplTest {

    CarMakeDao carMakeDao;

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
