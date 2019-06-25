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
     * Gets car make.
     *
     * @param index the index
     * @return the car make
     */
    CarMake getCarMake(int index);
}
