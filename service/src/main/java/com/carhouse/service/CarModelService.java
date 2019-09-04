package com.carhouse.service;

import com.carhouse.model.CarModel;
import javassist.NotFoundException;

import java.util.List;

/**
 * The interface of carModel service.
 * provides methods to get CarModel models.
 *
 * @author Katuranau Maksimilyan
 * @see CarModel
 */
public interface CarModelService {
    /**
     * Gets car models of selected car make.
     *
     * @param carMakeId the car make id to get car models
     * @return the list of car models
     * @throws NotFoundException if there is not car make with selected id
     */
    List<CarModel> getCarModels(Integer carMakeId) throws NotFoundException;

    /**
     * Gets car model by id.
     *
     * @param id the car model id.
     * @return the car model
     * @throws NotFoundException if there is not car model with selected id
     */
    CarModel getCarModel(int id) throws NotFoundException;
}
