package com.carhouse.dao.impl;

import com.carhouse.dao.CarSaleDao;
import com.carhouse.model.Car;
import com.carhouse.model.User;
import com.carhouse.model.CarSale;
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
        assertEquals(2, carSale.getCar().getCarId());
    }

    @Test
    void getNonExistentCarSale() {
        assertThrows(EmptyResultDataAccessException.class, () -> carSaleDao.getCarSale(9));
    }

    @Test
    void addCarSale() {
        int size = carSaleDao.getCarSales().size();
        CarSale newCarSale = new CarSale(5, new BigDecimal(23200), new Date(), new User(1),
                new Car(3));
        int index = carSaleDao.addCarSale(newCarSale);
        CarSale obtainedCarSale = carSaleDao.getCarSale(index);
        assertEquals(size + 1, carSaleDao.getCarSales().size());
        assertEquals(newCarSale.getPrice(), obtainedCarSale.getPrice());
        assertEquals(newCarSale.getUser().getUserId(), obtainedCarSale.getUser().getUserId());
    }

    @Test
    void addCarSaleWithWrongReference() {
        assertThrows(DataIntegrityViolationException.class, () -> carSaleDao.addCarSale(new CarSale(4,
                new BigDecimal(3200), new Date(), new User(5), new Car(15))));
    }

    @Test
    void updateCarSale() {
        CarSale newCarSale = new CarSale(4, new BigDecimal(13200), new Date(), new User(1),
                new Car(5));
        carSaleDao.updateCarSale(newCarSale);
        CarSale obtainedCarSale = carSaleDao.getCarSale(4);
        assertEquals(newCarSale.getPrice(), obtainedCarSale.getPrice());
        assertEquals(newCarSale.getUser().getUserId(), obtainedCarSale.getUser().getUserId());
        assertEquals(newCarSale.getCar().getCarId(), obtainedCarSale.getCar().getCarId());
    }

    @Test
    void updateCarSaleWithWrongReference() {
        assertThrows(DataIntegrityViolationException.class, () -> carSaleDao.updateCarSale(new CarSale(4,
                new BigDecimal(3200), new Date(), new User(5), new Car(15))));
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
