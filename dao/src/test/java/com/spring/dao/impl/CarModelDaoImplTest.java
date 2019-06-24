package com.spring.dao.impl;

import com.spring.dao.CarModelDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.junit.jupiter.api.Assertions.*;

@SpringTestConfiguration
class CarModelDaoImplTest {

    CarModelDao carModelDao;

    @Autowired
    CarModelDaoImplTest(CarModelDao carModelDao) {
        this.carModelDao = carModelDao;
    }

    @Test
    void getCarModels() {
        assertEquals(3, carModelDao.getCarModels().size());
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
