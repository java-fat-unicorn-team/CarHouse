package com.spring.rest.dao;

import com.spring.rest.model.CarMake;

import java.util.List;

/**
 * The interface provides methods to get CarMake model.
 */
public interface CarMakeDao {
    /**
     * Gets all car makes.
     *
     * @return the all car makes
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
