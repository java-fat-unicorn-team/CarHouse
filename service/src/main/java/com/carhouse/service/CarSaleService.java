package com.carhouse.service;

import com.carhouse.model.CarSale;
import com.carhouse.model.dto.CarSaleDto;
import javassist.NotFoundException;

import java.util.List;
import java.util.Map;

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
     * Gets car sales dto.
     *
     * @param conditionParams the conditions params
     * @return the car sales dto
     */
    List<CarSaleDto> getCarSalesDto(Map<String, String> conditionParams);

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
     * @throws NotFoundException throws if there is not such car sale to update
     */
    void updateCarSale(CarSale carSale) throws NotFoundException;

    /**
     * Delete car sale by id.
     *
     * @param carSaleId the car sale id
     * @throws NotFoundException throws if there is not such car sale to delete
     */
    void deleteCarSale(int carSaleId) throws NotFoundException;
}
