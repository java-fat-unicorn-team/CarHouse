package com.spring.rest.dao;

import com.spring.rest.model.CarSale;

import java.math.BigDecimal;
import java.util.List;

public interface CarSaleDao {
    List<CarSale> getAllCarSales();

    CarSale getCarSale(int index);

    void addCarSale(BigDecimal price, int userId, int carId);

    void updateCarSale(int index, BigDecimal price, int userId, int carId);

    void deleteCarSale(int index);
}
