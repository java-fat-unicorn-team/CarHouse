package com.carhouse.dao;

import com.carhouse.model.CarSale;

import java.util.List;

/**
 * The interface provides methods to manage CarSale model.
 * @author Katuranau Maksimilyan
 * @see CarSale
 */
public interface CarSaleDao {
    /**
     * Gets car sales.
     *
     * @return the list of car sales
     */
    List<CarSale> getCarSales();

    /**
     * Gets car sale by id.
     *
     * @param id the car sale id
     * @return the list of car sale
     */
    CarSale getCarSale(final int id);


    /**
     * Add car sale.
     *
     * @param carSale the car sale
     * @return car sale id
     */
    Integer addCarSale(CarSale carSale);


    /**
     * Update car sale.
     * Gets car sale id from carSale object
     *
     * @param carSale the car sale
     */
    void updateCarSale(CarSale carSale);

    /**
     * Delete car sale by id.
     *
     * @param id the car sale id
     */
    void deleteCarSale(final int id);
}
