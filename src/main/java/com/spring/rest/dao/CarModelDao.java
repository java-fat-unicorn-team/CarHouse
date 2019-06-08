package com.spring.rest.dao;

import com.spring.rest.model.CarModel;

import java.util.List;

/**
 * The interface Car model dao.
 */
public interface CarModelDao {
    /**
     * Gets all car models.
     *
     * @return the all car models
     */
    List<CarModel> getAllCarModels();

    /**
     * Gets car model.
     *
     * @param index the index
     * @return the car model
     */
    CarModel getCarModel(int index);
}
