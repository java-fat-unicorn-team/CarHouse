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
 * The class provides method to get list of FuelType models on service layer.
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
}
