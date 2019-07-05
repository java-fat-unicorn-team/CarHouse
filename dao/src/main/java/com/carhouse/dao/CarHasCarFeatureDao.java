package com.carhouse.dao;


/**
 * The interface provides methods to manage references between car and CarFeature models.
 *
 * @author Katuranau Maksimilyan
 */
public interface CarHasCarFeatureDao {
    /**
     * add car feature to car by id.
     *
     * @param carId        car id
     * @param carFeatureId id of car feature to add
     */
    void addCarFeatureToCar(int carId, int carFeatureId);

    /**
     * delete car feature from car by id.
     *
     * @param carId        car id
     * @param carFeatureId id of car feature to delete
     */
    void deleteCarFeatureFromCar(int carId, int carFeatureId);

    /**
     * delete all references between car with provided id and car features.
     *
     * @param carId id of car for which to delete all references
     */
    void deleteCarFeatureListFromCar(int carId);
}
