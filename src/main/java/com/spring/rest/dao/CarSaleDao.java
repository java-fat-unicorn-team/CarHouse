package com.spring.rest.dao;

import com.spring.rest.model.CarSale;

import java.math.BigDecimal;
import java.util.List;

/**
 * The interface Car sale dao.
 */
public interface CarSaleDao {
    /**
     * Gets all car sales.
     *
     * @return the all car sales
     */
    List<CarSale> getAllCarSales();

    /**
     * Gets car sale.
     *
     * @param index the index
     * @return the car sale
     */
    CarSale getCarSale(int index);

    /**
     * Add car sale.
     *
     * @param price  the price
     * @param userId the user id
     * @param carId  the car id
     */
    void addCarSale(BigDecimal price, int userId, int carId);

    /**
     * Update car sale.
     *
     * @param index  the index
     * @param price  the price
     * @param userId the user id
     * @param carId  the car id
     */
    void updateCarSale(int index, BigDecimal price, int userId, int carId);

    /**
     * Delete car sale.
     *
     * @param index the index
     */
    void deleteCarSale(int index);
}
