package com.spring.rest.dao;

import com.spring.rest.model.CarModel;

import java.util.List;

/**
 * The interface provides methods to get CarModel model.
 */
public interface CarModelDao {
    /**
     * Gets all car models.
     *
     * @return the all car models
     */
    List<CarModel> getCarModels();

    /**
     * Gets car model.
     *
     * @param index the index
     * @return the car model
     */
    CarModel getCarModel(int index);
}
