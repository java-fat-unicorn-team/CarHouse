package com.carhouse.dao;

import com.carhouse.model.FuelType;

import java.util.List;

/**
 * The interface provides methods to manage FuelType model.
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

    /**
     * Gets fuel type by id.
     *
     * @param id the fuel type id.
     * @return the fuel type
     */
    FuelType getFuelType(final int id);

    /**
     * Add fuel type.
     *
     * @param fuelType the fuel type
     * @return fuel type id
     */
    Integer addFuelType(String fuelType);

    /**
     * Update fuel type by id.
     *
     * @param id        the fuel type id.
     * @param fuelType  the fuel type
     */
    void updateFuelType(final int id, final String fuelType);

    /**
     * Delete fuel type by id.
     *
     * @param id the fuel type id.
     */
    void deleteFuelType(final int id);
}
