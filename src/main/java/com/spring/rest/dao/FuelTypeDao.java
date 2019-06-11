package com.spring.rest.dao;

import com.spring.rest.model.FuelType;

import java.util.List;

/**
 * The interface provides CRUD operations with FuelType model.
 */
public interface FuelTypeDao {
    /**
     * Gets all fuel types.
     *
     * @return the all fuel types
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
     */
    void addFuelType(String fuelType);

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
