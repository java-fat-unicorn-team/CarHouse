package com.carhouse.service.impl;

import com.carhouse.dao.FuelTypeDao;
import com.carhouse.model.FuelType;
import com.carhouse.service.FuelTypeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The class provides methods to manage FuelType model.
 *
 * @author Katuranau Maksimilyan
 */
@Service
public class FuelTypeServiceImpl implements FuelTypeService {

    private FuelTypeDao fuelTypeDao;

    /**
     * Instantiates a new Fuel type service.
     *
     * @param fuelTypeDao the fuel type dao
     */
    @Autowired
    public FuelTypeServiceImpl(final FuelTypeDao fuelTypeDao) {
        this.fuelTypeDao = fuelTypeDao;
    }

    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger(FuelTypeServiceImpl.class);

    /**
     * Gets fuel types.
     *
     * @return the list of fuel types
     */
    @Override
    public List<FuelType> getFuelTypes() {
        LOGGER.debug("method getFuelTypes was called");
        return fuelTypeDao.getFuelTypes();
    }

    /**
     * Gets fuel type.
     *
     * @param index the index
     * @return the fuel type
     */
    @Override
    public FuelType getFuelType(final int index) {
        LOGGER.debug("method getFuelType with parameter: {} was called", index);
        return fuelTypeDao.getFuelType(index);
    }

    /**
     * Add fuel type.
     *
     * @param fuelType the fuel type
     * @return fuel type id
     */
    @Override
    public Integer addFuelType(final String fuelType) {
        LOGGER.debug("method addFuelType with parameter: {} was called", fuelType);
        return fuelTypeDao.addFuelType(fuelType);
    }

    /**
     * Delete fuel type.
     *
     * @param index the index
     */
    @Override
    public void deleteFuelType(final int index) {
        LOGGER.debug("method deleteFuelType with parameter: {} was called", index);
        fuelTypeDao.deleteFuelType(index);
    }

    /**
     * Update fuel type.
     *
     * @param index    the index
     * @param fuelType the fuel type
     */
    @Override
    public void updateFuelType(final int index, final String fuelType) {
        LOGGER.debug("method updateFuelType with parameters: {}, {} was called", index, fuelType);
        fuelTypeDao.updateFuelType(index, fuelType);
    }
}
