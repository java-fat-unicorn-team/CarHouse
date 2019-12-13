package com.carhouse.service;

import com.carhouse.model.CarSale;
import com.carhouse.model.dto.CarSaleDto;
import javassist.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

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
     * @param conditionParams the conditions params
     * @return the car sales dto
     */
    List<CarSaleDto> getListCarSales(Map<String, String> conditionParams);

    /**
     * Gets last five car sales.
     *
     * @return the car sales dto
     */
    List<CarSaleDto> getListLastFiveCarSales();

    /**
     * Gets user car sales.
     *
     * @param login the user name to get car sales
     * @return the car sales dto
     */
    List<CarSaleDto> getListUserCarSales(String login);

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
     * @param file    the image file
     * @return car sale id
     */
    Integer addCarSale(CarSale carSale, MultipartFile file);


    /**
     * Update car sale.
     * Gets car sale id from carSale object
     *
     * @param carSale the car sale
     * @param file    the image file
     * @throws NotFoundException throws if there is not such car sale to update
     */
    void updateCarSale(CarSale carSale, MultipartFile file) throws NotFoundException;

    /**
     * Delete car sale by id.
     *
     * @param carSaleId the car sale id
     * @throws NotFoundException throws if there is not such car sale to delete
     */
    void deleteCarSale(int carSaleId) throws NotFoundException;
}
