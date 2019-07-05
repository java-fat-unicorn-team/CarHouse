package com.carhouse.dao;

import com.carhouse.model.CarModel;

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
     * Gets car model by id.
     *
     * @param id the car model id.
     * @return the car model
     */
    CarModel getCarModel(int id);
}
