package com.carhouse.dao.impl;

import com.carhouse.dao.CarSaleDao;
import com.carhouse.dao.config.TestConfiguration;
import com.carhouse.dao.config.TestSpringJDBCConfig;
import com.carhouse.model.Car;
import com.carhouse.model.CarSale;
import com.carhouse.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class, TestSpringJDBCConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CarSaleDaoImplTest {

    private CarSaleDao carSaleDao;

    @Autowired
    CarSaleDaoImplTest(CarSaleDao carSaleDao) {
        this.carSaleDao = carSaleDao;
    }

    @Test
    void getListCarSales() {
        assertEquals(4, carSaleDao.getListCarSales(new HashMap<>()).size());
    }

    @Test
    void getListCarSalesWithSelectedCarMake() {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("carMakeId", "2");
        assertEquals(2, carSaleDao.getListCarSales(requestParams).size());
    }

    @Test
    void getListCarSalesWithSelectedCarMakeAndCarModel() {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("carMakeId", "2");
        requestParams.put("carModelId", "3");
        assertEquals(1, carSaleDao.getListCarSales(requestParams).size());
    }

    @Test
    void getListCarSalesWithAllFilters() {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("carMakeId", "1");
        requestParams.put("carModelId", "1");
        requestParams.put("yearFrom", "2014-01-01");
        requestParams.put("yearTo", "2018-01-01");
        requestParams.put("priceFrom", "30000");
        requestParams.put("priceTo", "40000");
        assertEquals(1, carSaleDao.getListCarSales(requestParams).size());
    }

    @Test
    void getCarSale() {
        CarSale carSale = carSaleDao.getCarSale(2);
        assertEquals(new BigDecimal(40500), carSale.getPrice());
        assertEquals(1, carSale.getUser().getUserId());
        assertEquals(2, carSale.getCar().getCarId());
        assertEquals(2, carSale.getCar().getCarFeatureList().size());
        assertEquals(2, carSale.getCommentList().size());
    }

    @Test
    void getNonExistentCarSale() {
        EmptyResultDataAccessException thrown = assertThrows(EmptyResultDataAccessException.class,
                () -> carSaleDao.getCarSale(9));
        assertTrue(thrown.getMessage().contains("Incorrect result size: expected 1, actual 0"));
    }

    @Test
    void addCarSale() {
        int size = carSaleDao.getListCarSales(new HashMap<>()).size();
        CarSale newCarSale = new CarSale(5, new BigDecimal(23200), new Date(), new User(1),
                new Car(3), "");
        int index = carSaleDao.addCarSale(newCarSale);
        CarSale obtainedCarSale = carSaleDao.getCarSale(index);
        assertEquals(size + 1, carSaleDao.getListCarSales(new HashMap<>()).size());
        assertEquals(newCarSale.getPrice(), obtainedCarSale.getPrice());
        assertEquals(newCarSale.getUser().getUserId(), obtainedCarSale.getUser().getUserId());
        assertEquals(newCarSale.getImageName(), obtainedCarSale.getImageName());
    }

    @Test
    void addCarSaleWithWrongReference() {
        assertThrows(DataIntegrityViolationException.class, () -> carSaleDao.addCarSale(new CarSale(4,
                new BigDecimal(3200), new Date(), new User(5), new Car(15), "imageName")));
    }

    @Test
    void updateCarSale() {
        CarSale newCarSale = new CarSale(4, new BigDecimal(13200), new Date(), new User(1),
                new Car(5), "imageName");
        assertTrue(carSaleDao.updateCarSale(newCarSale));
        CarSale obtainedCarSale = carSaleDao.getCarSale(4);
        assertEquals(newCarSale.getPrice(), obtainedCarSale.getPrice());
        assertEquals(newCarSale.getUser().getUserId(), obtainedCarSale.getUser().getUserId());
        assertEquals(newCarSale.getCar().getCarId(), obtainedCarSale.getCar().getCarId());
        assertEquals(newCarSale.getImageName(), obtainedCarSale.getImageName());
    }

    @Test
    void updateCarSaleWithWrongReference() {
        assertThrows(DataIntegrityViolationException.class, () -> carSaleDao.updateCarSale(new CarSale(4,
                new BigDecimal(3200), new Date(), new User(5), new Car(15), "imageName")));
    }

    @Test
    void updateNotExistCarSaleCarSale() {
        assertFalse(carSaleDao.updateCarSale(new CarSale(14, new BigDecimal(13200), new Date(),
                new User(1), new Car(5), "imageName")));
    }

    @Test
    void deleteCarSale() {
        int size = carSaleDao.getListCarSales(new HashMap<>()).size();
        carSaleDao.deleteCarSale(3);
        assertEquals(size - 1, carSaleDao.getListCarSales(new HashMap<>()).size());
        assertThrows(EmptyResultDataAccessException.class, () -> carSaleDao.getCarSale(3));
    }

    @Test
    void deleteCarSaleWhichHaveReferencesShouldPass() {
        carSaleDao.deleteCarSale(4);
    }

    @Test
    void deleteNotExistCarSale() {
        assertThrows(EmptyResultDataAccessException.class, () -> carSaleDao.deleteCarSale(10));
    }
}
