package com.spring.rest.dao.impl;

import com.spring.rest.dao.CarSaleDao;
import com.spring.rest.model.CarCharacteristics;
import com.spring.rest.model.CarSale;
import com.spring.rest.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringTestConfiguration
class CarSaleDaoImplTest {

    CarSaleDao carSaleDao;

    @Autowired
    CarSaleDaoImplTest(CarSaleDao carSaleDao) {
        this.carSaleDao = carSaleDao;
    }

    @Test
    void getCarSales() {
        assertEquals(4, carSaleDao.getCarSales().size());
    }

    @Test
    void getCarSale() {
        CarSale carSale = carSaleDao.getCarSale(2);
        assertEquals(new BigDecimal(40500), carSale.getPrice());
        assertEquals(1, carSale.getUser().getUserId());
        assertEquals(2, carSale.getCarCharacteristics().getCarId());
    }

    @Test
    void getNonExistentCarSale() {
        assertThrows(EmptyResultDataAccessException.class, () -> carSaleDao.getCarSale(9));
    }

    @Test
    void addCarSale() {
        int size = carSaleDao.getCarSales().size();
        int index = carSaleDao.addCarSale(new CarSale(5, new BigDecimal(23200), new Date(),
                new User(1), new CarCharacteristics(3)));
        assertEquals(size + 1, carSaleDao.getCarSales().size());
        CarSale carSale = carSaleDao.getCarSale(index);
        assertEquals(new BigDecimal(23200), carSale.getPrice());
        assertEquals(1, carSale.getUser().getUserId());
    }

    @Test
    void addCarSaleWithWrongReference() {
        assertThrows(DataIntegrityViolationException.class, () -> carSaleDao.addCarSale(new CarSale(4,
                new BigDecimal(3200), new Date(), new User(5), new CarCharacteristics(15))));
    }

    @Test
    void updateCarSale() {
        carSaleDao.updateCarSale(new CarSale(4, new BigDecimal(13200), new Date(),
                new User(1), new CarCharacteristics(5)));
        CarSale carSale = carSaleDao.getCarSale(4);
        assertEquals(new BigDecimal(13200), carSale.getPrice());
        assertEquals(1, carSale.getUser().getUserId());
        assertEquals(5, carSale.getCarCharacteristics().getCarId());
    }

    @Test
    void updateCarSaleWithWrongReference() {
        assertThrows(DataIntegrityViolationException.class, () -> carSaleDao.updateCarSale(new CarSale(4,
                new BigDecimal(3200), new Date(), new User(5), new CarCharacteristics(15))));
    }

    @Test
    void deleteCarSale() {
        int size = carSaleDao.getCarSales().size();
        carSaleDao.deleteCarSale(3);
        assertEquals(size - 1, carSaleDao.getCarSales().size());
        assertThrows(EmptyResultDataAccessException.class, () -> carSaleDao.getCarSale(3));
    }

    @Test
    void deleteCarSaleWhichHaveReferences() {
        assertThrows(DataIntegrityViolationException.class, () -> carSaleDao.deleteCarSale(4));
    }
}
