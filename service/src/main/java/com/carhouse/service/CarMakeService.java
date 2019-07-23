package com.carhouse.service;

import com.carhouse.model.CarMake;

import java.util.List;

/**
 * The interface of carMake service.
 * provides methods to get CarMake models.
 * @see CarMake
 * @author Katuranau Maksimilyan
 */
public interface CarMakeService {
    /**
     * Gets car makes.
     *
     * @return the list of car makes
     */
    List<CarMake> getCarMakes();

    /**
     * Gets car make by id.
     *
     * @param id the car make id
     * @return the car make
     */
    CarMake getCarMake(int id);
}
