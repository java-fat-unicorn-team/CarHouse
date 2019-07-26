package com.carhouse.dao;

import com.carhouse.model.CarFeature;

import java.util.List;

/**
 * The interface provides methods to get CarFeature models.
 *
 * @author Katuranau Maksimilyan
 * @see CarFeature
 */
public interface CarFeatureDao {
    /**
     * Gets all features of car with id which is provided.
     *
     * @param carId the car id
     * @return the list of car features
     */
    List<CarFeature> getCarFeatures(int carId);

    /**
     * Gets all possible car features.
     *
     * @return the list of features
     */
    List<CarFeature> getAllFeatures();
}
