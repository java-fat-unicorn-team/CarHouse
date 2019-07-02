package com.carhouse.service;

import com.carhouse.model.FuelType;

import java.util.List;

/**
 * The interface of fuel type service.
 * Provides methods to manage fuel type model on the service layer
 * @author Katuranau Maksimilyan
 */
public interface FuelTypeService {
    /**
     * Gets fuel types.
     *
     * @return the list of fuel types
     */
    List<FuelType> getFuelTypes();

    /**
     * Gets fuel type by id.
     *
     * @param id the fuel type id
     * @return the fuel type
     */
    FuelType getFuelType(int id);

    /**
     * Add fuel type.
     * Fuel type id is auto generated
     * Add fuel type to the end of list
     *
     * @param fuelType the fuel type
     * @return fuel type id
     */
    Integer addFuelType(String fuelType);

    /**
     * Update fuel type by id.
     *
     * @param id    the fuel type id
     * @param fuelType the fuel type
     */
    void updateFuelType(int id, String fuelType);

    /**
     * Delete fuel type by id.
     *
     * @param id the fuel type id
     */
    void deleteFuelType(int id);
}
