package com.spring.rest.dao;

import com.spring.rest.model.CarModel;

import java.util.List;

/**
 * The interface provides methods to manage CarModel model.
 * @author Katuranau Maksimilyan
 * @see CarModel
 */
public interface CarModelDao {
    /**
     * Gets car models.
     *
     * @return the list of car models
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
