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
 * The class provides methods to manage FuelType model on service layer.
 * It is realisation of FuelTypeService interface
 * @see FuelTypeService
 * @author Katuranau Maksimilyan
 */
@Service
public class FuelTypeServiceImpl implements FuelTypeService {

    private FuelTypeDao fuelTypeDao;

    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger(FuelTypeServiceImpl.class);

    /**
     * Instantiates a new Fuel type service.
     *
     * @param fuelTypeDao the class is provided CRUD operations for fuel type model.
     */
    @Autowired
    public FuelTypeServiceImpl(final FuelTypeDao fuelTypeDao) {
        this.fuelTypeDao = fuelTypeDao;
    }

    /**
     * Gets fuel types.
     *
     * @return the list of fuel types
     */
    @Override
    public List<FuelType> getFuelTypes() {
        LOGGER.debug("method getFuelTypes");
        return fuelTypeDao.getFuelTypes();
    }

    /**
     * Gets fuel type by id.
     *
     * @param id the fuel type id
     * @return the fuel type
     */
    @Override
    public FuelType getFuelType(final int id) {
        LOGGER.debug("method getFuelType with parameter: [{}]", id);
        return fuelTypeDao.getFuelType(id);
    }

    /**
     * Add fuel type.
     * Fuel type id is auto generated
     *
     * @param fuelType the fuel type
     * @return fuel type id
     */
    @Override
    public Integer addFuelType(final String fuelType) {
        LOGGER.debug("method addFuelType with parameter: [{}]", fuelType);
        return fuelTypeDao.addFuelType(fuelType);
    }

    /**
     * Update fuel type by id.
     *
     * @param id    the fuel type id
     * @param fuelType the fuel type
     */
    @Override
    public void updateFuelType(final int id, final String fuelType) {
        LOGGER.debug("method updateFuelType with parameters: [{}, {}]", id, fuelType);
        fuelTypeDao.updateFuelType(id, fuelType);
    }

    /**
     * Delete fuel type by id.
     *
     * @param id the fuel type id
     */
    @Override
    public void deleteFuelType(final int id) {
        LOGGER.debug("method deleteFuelType with parameter: [{}]", id);
        fuelTypeDao.deleteFuelType(id);
    }
}
