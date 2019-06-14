package com.spring.rest.dao;

import com.spring.rest.model.FuelType;

import java.util.List;

/**
 * The interface provides methods to manage FuelType model.
 */
public interface FuelTypeDao {
    /**
     * Gets fuel types.
     *
     * @return the list of fuel types
     */
    List<FuelType> getFuelTypes();

    /**
     * Gets fuel type.
     *
     * @param index the index
     * @return the fuel type
     */
    FuelType getFuelType(int index);

    /**
     * Add fuel type.
     *
     * @param fuelType the fuel type
     * @return fuel type id
     */
    Integer addFuelType(String fuelType);

    /**
     * Delete fuel type.
     *
     * @param index the index
     */
    void deleteFuelType(int index);

    /**
     * Update fuel type.
     *
     * @param index    the index
     * @param fuelType the fuel type
     */
    void updateFuelType(int index, String fuelType);
}
