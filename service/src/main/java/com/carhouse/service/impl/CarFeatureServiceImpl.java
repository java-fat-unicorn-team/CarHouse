package com.carhouse.service.impl;

import com.carhouse.dao.CarFeatureDao;
import com.carhouse.model.CarFeature;
import com.carhouse.service.CarFeatureService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The class provides method to get CarFeature models on service layer.
 * It is realisation of CarFeatureService interface
 *
 * @author Katuranau Maksimilyan
 * @see CarFeatureService
 */
@Service
public class CarFeatureServiceImpl implements CarFeatureService {

    private CarFeatureDao carFeatureDao;

    private static final Logger LOGGER = LogManager.getLogger(CarFeatureServiceImpl.class);

    /**
     * Instantiates a new Car feature service.
     *
     * @param carFeatureDao the class is provided CRUD operations for fuel type model.
     */
    @Autowired
    public CarFeatureServiceImpl(final CarFeatureDao carFeatureDao) {
        this.carFeatureDao = carFeatureDao;
    }

    /**
     * Gets all features of car with id which is provided.
     *
     * @param carId the car id
     * @return the list of car features
     */
    @Override
    public List<CarFeature> getCarFeatures(final int carId) {
        LOGGER.debug("method getCarFeatures with parameter: [{}]", carId);
        return carFeatureDao.getCarFeatures(carId);
    }

    /**
     * Gets all possible car features.
     *
     * @return the list of features
     */
    @Override
    public List<CarFeature> getAllFeatures() {
        return carFeatureDao.getAllFeatures();
    }
}
