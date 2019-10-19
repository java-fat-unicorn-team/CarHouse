package com.carhouse.dao;

import com.carhouse.model.CarSale;
import com.carhouse.model.dto.CarSaleDto;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.FileSystemException;
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
     * @return check or car sale is updated
     * @throws FileSystemException the file system exception when writing file
     */
    boolean updateCarSale(CarSale carSale, MultipartFile file) throws FileSystemException;

    /**
     * Delete car sale by id.
     *
     * @param carSaleId the car sale id
     * @return check or car sale is deleted
     */
    boolean deleteCarSale(int carSaleId);
}
