package com.carhouse.service;

import com.carhouse.model.CarSale;
import com.carhouse.model.dto.CarSaleDto;
import javassist.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.FileSystemException;
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
     * @throws FileSystemException the file system exception when writing file
     */
    Integer addCarSale(CarSale carSale, MultipartFile file) throws FileSystemException;


    /**
     * Update car sale.
     * Gets car sale id from carSale object
     *
     * @param carSale the car sale
     * @param file    the image file
     * @throws NotFoundException throws if there is not such car sale to update
     * @throws FileSystemException the file system exception when writing file
     */
    void updateCarSale(CarSale carSale, MultipartFile file) throws NotFoundException, FileSystemException;

    /**
     * Delete car sale by id.
     *
     * @param carSaleId the car sale id
     * @throws NotFoundException throws if there is not such car sale to delete
     */
    void deleteCarSale(int carSaleId) throws NotFoundException;
}
