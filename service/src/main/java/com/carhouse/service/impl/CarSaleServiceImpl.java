package com.carhouse.service.impl;

import com.carhouse.dao.CarDao;
import com.carhouse.dao.CarSaleDao;
import com.carhouse.model.CarSale;
import com.carhouse.model.dto.CarSaleDto;
import com.carhouse.service.CarSaleService;
import com.carhouse.service.exception.WrongReferenceException;
import javassist.NotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.FileSystemException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The class provides method to manage CarSale models on service layer.
 * It is realisation of CarSaleService interface
 *
 * @author Katuranau Maksimilyan
 * @see CarSaleService
 */
@Service
public class CarSaleServiceImpl implements CarSaleService {

    private CarSaleDao carSaleDao;
    private CarDao carDao;


    private static final Logger LOGGER = LogManager.getLogger(CarSaleServiceImpl.class);

    /**
     * Instantiates a new Car sale service.
     *
     * @param carSaleDao the class provides CRUD operations for fuel type model.
     * @param carDao     the car dao is used to set changes in car object and then use its id in carSale object
     */
    @Autowired
    public CarSaleServiceImpl(final CarSaleDao carSaleDao, final CarDao carDao) {
        this.carSaleDao = carSaleDao;
        this.carDao = carDao;
    }

    /**
     * Gets car sales dto.
     * Validate request params and send to dao only valid params
     *
     * @param conditionParams the conditions params
     * @return the car sales dto
     */
    @Override
    public List<CarSaleDto> getListCarSales(final Map<String, String> conditionParams) {
        LOGGER.debug("method getCarSalesDto");
        Map<String, String> validParams = new HashMap<>();
        for (Map.Entry<String, String> param : conditionParams.entrySet()) {
            if (StringUtils.isNumeric(param.getValue())
                    || GenericValidator.isDate(param.getValue(), "yyyy-MM-dd", true)) {
                validParams.put(param.getKey(), param.getValue());
            }
        }
        return carSaleDao.getListCarSales(validParams);
    }

    /**
     * Gets car sale by id.
     *
     * @param carSaleId the car sale id
     * @return the list of car sale
     * @throws NotFoundException throws if there is not such car sale
     */
    @Override
    public CarSale getCarSale(final int carSaleId) throws NotFoundException {
        LOGGER.debug("method getCarSale with parameter: [{}]", carSaleId);
        try {
            return carSaleDao.getCarSale(carSaleId);
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException("there is not car sale with id = " + carSaleId);
        }
    }

    /**
     * Add car sale.
     * First add car object from carSale object and then use returned car id to add carSale object
     *
     * @param carSale the car sale
     * @param file    the image file
     * @return car sale id
     * @throws FileSystemException the file system exception when writing file
     */
    @Override
    public Integer addCarSale(final CarSale carSale, final MultipartFile file) throws FileSystemException {
        LOGGER.debug("method addCarSale with parameter: [{}]", carSale);
        try {
            int carId = carDao.addCar(carSale.getCar());
            carSale.getCar().setCarId(carId);
            return carSaleDao.addCarSale(carSale, file);
        } catch (DataIntegrityViolationException ex) {
            throw new WrongReferenceException("there is wrong references in your car sale");
        }
    }

    /**
     * Update car sale.
     * Gets car sale id from carSale object
     * Use carDao to update car object from carSale object
     *
     * @param carSale the car sale
     * @param file    the image file
     * @throws NotFoundException throws if there is not such car sale to update
     * @throws FileSystemException the file system exception when writing file
     */
    @Override
    public void updateCarSale(final CarSale carSale, final MultipartFile file) throws NotFoundException,
            FileSystemException {
        LOGGER.debug("method updateCarSale with parameter: [{}]", carSale);
        try {
            if (!carDao.updateCar(carSale.getCar())) {
                throw new NotFoundException("there is not car with id = " + carSale.getCar().getCarId());
            }
            if (!carSaleDao.updateCarSale(carSale, file)) {
                throw new NotFoundException("there is not car sale with id = " + carSale.getCarSaleId());
            }
        } catch (DataIntegrityViolationException ex) {
            throw new WrongReferenceException("there is wrong references in your car sale");
        }
    }

    /**
     * Delete car sale by id.
     *
     * @param carSaleId the car sale id
     * @throws NotFoundException throws if there is not such car sale to delete
     */
    @Override
    public void deleteCarSale(final int carSaleId) throws NotFoundException {
        LOGGER.debug("method deleteCarSale with parameter: [{}]", carSaleId);
        if (!carSaleDao.deleteCarSale(carSaleId)) {
            throw new NotFoundException("there is not car sale with id = " + carSaleId + " to delete");
        }
    }
}
