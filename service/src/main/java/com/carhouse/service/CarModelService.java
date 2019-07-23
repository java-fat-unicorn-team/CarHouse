package com.carhouse.service;

import com.carhouse.model.CarModel;

import java.util.List;

/**
 * The interface of carModel service.
 * provides methods to get CarModel models.
 * @author Katuranau Maksimilyan
 * @see CarModel
 */
public interface CarModelService {
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
