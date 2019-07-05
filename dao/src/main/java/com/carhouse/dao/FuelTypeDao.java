package com.carhouse.dao;

import com.carhouse.model.FuelType;

import java.util.List;

/**
 * The interface provides methods to get FuelType models.
 * @author Katuranau Maksimilyan
 * @see FuelType
 */
public interface FuelTypeDao {
    /**
     * Gets fuel types.
     *
     * @return the list of fuel types
     */
    List<FuelType> getFuelTypes();
}
