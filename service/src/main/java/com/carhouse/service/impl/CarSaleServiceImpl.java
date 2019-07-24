package com.carhouse.service.impl;

import com.carhouse.dao.CarSaleDao;
import com.carhouse.model.CarSale;
import com.carhouse.service.CarSaleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
     */
    @Override
    public CarSale getCarSale(final int carSaleId) {
        LOGGER.debug("method getCarSale with parameter: [{}]", carSaleId);
        return carSaleDao.getCarSale(carSaleId);
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
        return carSaleDao.addCarSale(carSale);
    }

    /**
     * Update car sale.
     * Gets car sale id from carSale object
     *
     * @param carSale the car sale
     */
    @Override
    public void updateCarSale(final CarSale carSale) {
        LOGGER.debug("method updateCarSale with parameter: [{}]", carSale);
        carSaleDao.updateCarSale(carSale);
    }

    /**
     * Delete car sale by id.
     *
     * @param carSaleId the car sale id
     */
    @Override
    public void deleteCarSale(final int carSaleId) {
        LOGGER.debug("method deleteCarSale with parameter: [{}]", carSaleId);
        carSaleDao.deleteCarSale(carSaleId);
    }
}
