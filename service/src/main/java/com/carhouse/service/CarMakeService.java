package com.carhouse.service;

import com.carhouse.model.CarMake;
import javassist.NotFoundException;

import java.util.List;

/**
 * The interface of carMake service.
 * provides methods to get CarMake models.
 *
 * @author Katuranau Maksimilyan
 * @see CarMake
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
     * @throws NotFoundException if there is not car make with selected id
     */
    CarMake getCarMake(int id) throws NotFoundException;
}
