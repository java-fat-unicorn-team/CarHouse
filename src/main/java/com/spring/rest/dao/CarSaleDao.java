package com.spring.rest.dao;

import com.spring.rest.model.CarSale;

import java.util.List;

/**
 * The interface provides CRUD operations with CarSale model.
 */
public interface CarSaleDao {
    /**
     * Gets all car sales.
     *
     * @return the all car sales
     */
    List<CarSale> getCarSales();

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
     * @param carSale the car sale
     */
    void addCarSale(CarSale carSale);


    /**
     * Update car sale.
     *
     * @param carSale the car sale
     */
    void updateCarSale(CarSale carSale);

    /**
     * Delete car sale.
     *
     * @param index the index
     */
    void deleteCarSale(int index);
}
