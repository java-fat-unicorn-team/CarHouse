package com.carhouse.service;

import com.carhouse.model.CarSale;
import javassist.NotFoundException;

import java.util.List;

/**
 * The interface of carSale service.
 * provides methods to manage CarSale model.
 *
 * @author Katuranau Maksimilyan
 * @see CarSale
 */
public interface CarSaleService {
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
     * @throws NotFoundException throws if there is not such car sale
     */
    CarSale getCarSale(int carSaleId) throws NotFoundException;


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
     * @return check or car sale is updated
     * @throws NotFoundException throws if there is not such car sale to update
     */
    boolean updateCarSale(CarSale carSale) throws NotFoundException;

    /**
     * Delete car sale by id.
     *
     * @param carSaleId the car sale id
     * @return check or car sale is deleted
     * @throws NotFoundException throws if there is not such car sale to delete
     */
    boolean deleteCarSale(int carSaleId) throws NotFoundException;
}
