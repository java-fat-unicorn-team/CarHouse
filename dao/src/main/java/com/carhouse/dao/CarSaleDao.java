package com.carhouse.dao;

import com.carhouse.model.CarSale;

import java.util.List;

/**
 * The interface provides methods to manage CarSale model.
 *
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
     * @param carSaleId the car sale id
     * @return the list of car sale
     */
    CarSale getCarSale(int carSaleId);


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
     * @return check or car sale is updated, 1 means that only one row has been updated otherwise it returns 0
     */
    boolean updateCarSale(CarSale carSale);

    /**
     * Delete car sale by id.
     *
     * @param carSaleId the car sale id
     * @return check or car sale is deleted, 1 means that only one row has been deleted otherwise it returns 0
     */
    boolean deleteCarSale(int carSaleId);
}
