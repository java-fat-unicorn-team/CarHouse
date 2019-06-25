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
     * Gets car features.
     *
     * @param carId the car id
     * @return the list of car features
     */
    List<CarFeature> getCarFeatures(int carId);

    /**
     * Gets car feature.
     *
     * @param index the index
     * @return the car feature
     */
    CarFeature getCarFeature(int index);

    /**
     * Add car feature.
     *
     * @param carFeature the car feature
     * @param carId      the car id
     * @return car feature id
     */
    Integer addCarFeature(String carFeature, int carId);

    /**
     * Update car feature.
     *
     * @param carFeature the car feature
     * @param carId      the car id
     */
    void updateCarFeature(String carFeature, int carId);

    /**
     * Delete car feature.
     *
     * @param index the index
     */
    void deleteCarFeature(int index);
}
