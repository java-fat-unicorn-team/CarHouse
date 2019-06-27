package com.carhouse.service;

import com.carhouse.model.FuelType;

import java.util.List;

/**
 * The interface of fuel type service.
 */
public interface FuelTypeService {
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
