package com.carhouse.dao;

import com.carhouse.model.CarMake;

import java.util.List;

/**
 * The interface provides methods to manage CarMake model.
 * @see CarMake
 * @author Katuranau Maksimilyan
 */
public interface CarMakeDao {
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
    CarMake getCarMake(final int id);
}
