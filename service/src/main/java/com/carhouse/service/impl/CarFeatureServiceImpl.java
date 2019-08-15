package com.carhouse.service.impl;

import com.carhouse.dao.CarDao;
import com.carhouse.dao.CarFeatureDao;
import com.carhouse.model.CarFeature;
import com.carhouse.service.CarFeatureService;
import javassist.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
    private CarDao carDao;

    private static final Logger LOGGER = LogManager.getLogger(CarFeatureServiceImpl.class);

    /**
     * Instantiates a new Car feature service.
     *
     * @param carFeatureDao the class is provided CRUD operations for fuel type model.
     * @param carDao        the car dao is provided CRUD operations for fuel type model and used to check or car exist
     */
    @Autowired
    public CarFeatureServiceImpl(final CarFeatureDao carFeatureDao, final CarDao carDao) {
        this.carFeatureDao = carFeatureDao;
        this.carDao = carDao;
    }

    /**
     * Gets all features of car with id which is provided.
     * Before getting the car features, the get method on the carDao interface is called to check or the car exists.
     *
     * @param carId the car id
     * @return the list of car features
     * @throws NotFoundException throws if there is not such car to get features
     */
    @Override
    public List<CarFeature> getCarFeatures(final int carId) throws NotFoundException {
        LOGGER.debug("method getCarFeatures with parameter: [{}]", carId);
        try {
            carDao.getCar(carId);
            return carFeatureDao.getCarFeatures(carId);
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException("there is not car with id = " + carId);
        }
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
