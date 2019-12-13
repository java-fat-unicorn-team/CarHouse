package com.carhouse.service;

import com.carhouse.model.CarFeature;
import javassist.NotFoundException;

import java.util.List;

/**
 * The interface of carFeature service.
 * provides methods to get CarFeature models.
 *
 * @author Katuranau Maksimilyan
 * @see CarFeature
 */
public interface CarFeatureService {
    /**
     * Gets all features of car with id which is provided.
     *
     * @param carId the car id
     * @return the list of car features
     * @throws NotFoundException throws if there is not such car to get features
     */
    List<CarFeature> getCarFeatures(int carId) throws NotFoundException;


    /**
     * Gets all possible car features.
     *
     * @return the list of features
     */
    List<CarFeature> getAllFeatures();
}
