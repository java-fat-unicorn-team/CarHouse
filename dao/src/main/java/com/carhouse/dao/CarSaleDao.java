package com.carhouse.dao;

import com.carhouse.model.CarSale;
import com.carhouse.model.dto.CarSaleDto;

import java.util.List;
import java.util.Map;

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
     * @param conditionParams the request params
     * @return the car sales dto
     */
    List<CarSaleDto> getListCarSales(Map<String, String> conditionParams);

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
     * @return check or car sale is updated
     */
    boolean updateCarSale(CarSale carSale);

    /**
     * Delete car sale by id.
     *
     * @param carSaleId the car sale id
     */
    void deleteCarSale(int carSaleId);

    /**
     * Gets car sale image name by carSale id.
     *
     * @param carSaleId the car sale id
     * @return the car sale image name
     */
    String getCarSaleImageName(int carSaleId);
}
