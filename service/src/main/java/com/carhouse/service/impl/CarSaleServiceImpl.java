package com.carhouse.service.impl;

import com.carhouse.dao.CarSaleDao;
import com.carhouse.model.CarSale;
import com.carhouse.service.CarSaleService;
import com.carhouse.service.exception.WrongReferenceException;
import javassist.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

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

    private static final Logger LOGGER = LogManager.getLogger(CarSaleServiceImpl.class);

    /**
     * Instantiates a new Car sale service.
     *
     * @param carSaleDao the class is provided CRUD operations for fuel type model.
     */
    @Autowired
    public CarSaleServiceImpl(final CarSaleDao carSaleDao) {
        this.carSaleDao = carSaleDao;
    }

    /**
     * Gets car sales.
     *
     * @return the list of car sales
     */
    @Override
    public List<CarSale> getCarSales() {
        LOGGER.debug("method getCarSales");
        return carSaleDao.getCarSales();
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
            throw new NotFoundException("there is not such car sale");
        }
    }

    /**
     * Add car sale.
     *
     * @param carSale the car sale
     * @return car sale id
     */
    @Override
    public Integer addCarSale(final CarSale carSale) {
        LOGGER.debug("method addCarSale with parameter: [{}]", carSale);
        try {
            return carSaleDao.addCarSale(carSale);
        } catch (DataIntegrityViolationException ex) {
            throw new WrongReferenceException("there is wrong references in your car sale");
        }
    }

    /**
     * Update car sale.
     * Gets car sale id from carSale object
     *
     * @param carSale the car sale
     * @return check or car sale is updated
     * @throws NotFoundException throws if there is not such car sale to update
     */
    @Override
    public boolean updateCarSale(final CarSale carSale) throws NotFoundException {
        LOGGER.debug("method updateCarSale with parameter: [{}]", carSale);
        getCarSale(carSale.getCarSaleId());
        try {
            return carSaleDao.updateCarSale(carSale);
        } catch (DataIntegrityViolationException ex) {
            throw new WrongReferenceException("there is wrong references in your car sale");
        }
    }

    /**
     * Delete car sale by id.
     *
     * @param carSaleId the car sale id
     * @return check or car sale is deleted
     * @throws NotFoundException throws if there is not such car sale to delete
     */
    @Override
    public boolean deleteCarSale(final int carSaleId) throws NotFoundException {
        LOGGER.debug("method deleteCarSale with parameter: [{}]", carSaleId);
        if (!carSaleDao.deleteCarSale(carSaleId)) {
            throw new NotFoundException("car sale with id = " + carSaleId + " you try to delete does not exist");
        }
        return true;
    }
}
