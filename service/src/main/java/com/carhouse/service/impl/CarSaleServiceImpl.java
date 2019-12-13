package com.carhouse.service.impl;

import com.carhouse.dao.CarDao;
import com.carhouse.dao.CarSaleDao;
import com.carhouse.model.CarSale;
import com.carhouse.model.dto.CarSaleDto;
import com.carhouse.service.CarSaleService;
import com.carhouse.service.exception.WrongReferenceException;
import com.carhouse.service.fileUpload.FileWriter;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * The class provides method to manage CarSale models on service layer.
 * It is realisation of CarSaleService interface
 *
 * @author Katuranau Maksimilyan
 * @see CarSaleService
 */
@Service
public class CarSaleServiceImpl implements CarSaleService {

    private FileWriter fileWriter;
    private CarSaleDao carSaleDao;
    private CarDao carDao;


    private static final Logger LOGGER = LogManager.getLogger(CarSaleServiceImpl.class);

    /**
     * Instantiates a new Car sale service.
     *
     * @param fileWriter the class is used to write file to the Apache server
     * @param carSaleDao the class provides CRUD operations for fuel type model.
     * @param carDao     the car dao is used to set changes in car object and then use its id in carSale object
     */
    @Autowired
    public CarSaleServiceImpl(final FileWriter fileWriter, final CarSaleDao carSaleDao, final CarDao carDao) {
        this.fileWriter = fileWriter;
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
     * Gets last five car sales.
     *
     * @return the car sales dto
     */
    @Override
    public List<CarSaleDto> getListLastFiveCarSales() {
        return carSaleDao.getListLastFiveCarSales();
    }

    /**
     * Gets user car sales.
     *
     * @param login the user name to get car sales
     * @return the car sales dto
     */
    @Override
    public List<CarSaleDto> getListUserCarSales(final String login) {
        LOGGER.debug("method getListUserCarSales with login: {}", login);
        return carSaleDao.getListUserCarSales(login);
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
     * Generate unique file name and write to the Apache server if file is not empty
     *
     * @param carSale the car sale
     * @param file    the image file
     * @return car sale id
     */
    @Override
    public Integer addCarSale(final CarSale carSale, final MultipartFile file) {
        LOGGER.debug("method addCarSale with parameter: [{}]", carSale);
        try {
            int carId = carDao.addCar(carSale.getCar());
            carSale.getCar().setCarId(carId);
            carSale.setImageName(!file.isEmpty() ? UUID.randomUUID().toString() : null);
            Integer carSaleId = carSaleDao.addCarSale(carSale);
            if (!file.isEmpty()) {
                fileWriter.writeFile(file, carSale.getImageName());
            }
            return carSaleId;
        } catch (DataIntegrityViolationException ex) {
            throw new WrongReferenceException("there is wrong references in your car sale");
        }
    }

    /**
     * Update car sale.
     * Gets car sale id from carSale object
     * Use carDao to update car object from carSale object
     * Generate unique file name and write to the Apache server if file is not empty @param carSale the car sale
     * Delete old image(browser caches images so the image should have a new name,
     * otherwise the browser will show old image)
     *
     * @param file the image file
     * @throws NotFoundException throws if there is not such car sale to update
     */
    @Override
    public void updateCarSale(final CarSale carSale, final MultipartFile file) throws NotFoundException {
        LOGGER.debug("method updateCarSale with parameter: [{}]", carSale);
        String oldCarSaleImageName = carSale.getImageName();
        carSale.setImageName(!file.isEmpty() ? UUID.randomUUID().toString() : oldCarSaleImageName);
        try {
            if (!carDao.updateCar(carSale.getCar())) {
                throw new NotFoundException("there is not car with id = " + carSale.getCar().getCarId());
            }
            if (!carSaleDao.updateCarSale(carSale)) {
                throw new NotFoundException("there is not car sale with id = " + carSale.getCarSaleId());
            }
        } catch (DataIntegrityViolationException ex) {
            throw new WrongReferenceException("there is wrong references in your car sale");
        }
        if (!file.isEmpty()) {
            fileWriter.deleteFile(oldCarSaleImageName);
            fileWriter.writeFile(file, carSale.getImageName());
        }
    }

    /**
     * Delete car sale by id.
     * Get image name from database by carSale id and delete this image from apache server
     *
     * @param carSaleId the car sale id
     * @throws NotFoundException throws if there is not such car sale to delete
     */
    @Override
    public void deleteCarSale(final int carSaleId) throws NotFoundException {
        LOGGER.debug("method deleteCarSale with parameter: [{}]", carSaleId);
        try {
            String imageName = carSaleDao.getCarSaleImageName(carSaleId);
            carSaleDao.deleteCarSale(carSaleId);
            fileWriter.deleteFile(imageName);
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException("there is not car sale with id = " + carSaleId + " to delete");
        }
    }
}
