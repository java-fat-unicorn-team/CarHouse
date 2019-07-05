package com.carhouse.dao;

import com.carhouse.model.CarFeature;

import java.util.List;

/**
 * The interface provides methods to manage CarFeature model.
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
     * Gets car feature by id.
     *
     * @param id the car feature id
     * @return the car feature
     */
    CarFeature getCarFeature(int id);

    /**
     * Add car feature to car with provided id.
     *
     * @param carFeature the car feature name
     * @param carId      the car id
     * @return car feature id
     */
    Integer addCarFeature(String carFeature, int carId);

    /**
     * Update car feature by id.
     *
     * @param carFeature   new car feature name
     * @param carFeatureId the car feature id
     */
    void updateCarFeature(String carFeature, int carFeatureId);

    /**
     * Delete car feature by id.
     *
     * @param id the index
     */
    void deleteCarFeature(int id);
}
