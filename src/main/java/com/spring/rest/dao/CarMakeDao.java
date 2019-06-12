package com.spring.rest.dao;

import com.spring.rest.model.CarMake;

import java.util.List;

/**
 * The interface provides methods to manage CarMake model.
 */
public interface CarMakeDao {
    /**
     * Gets car makes.
     *
     * @return the list of car makes
     */
    List<CarMake> getCarMakes();

    /**
     * Gets car make.
     *
     * @param index the index
     * @return the car make
     */
    CarMake getCarMake(int index);
}
