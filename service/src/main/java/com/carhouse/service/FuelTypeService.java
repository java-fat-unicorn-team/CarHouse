package com.carhouse.service;

import com.carhouse.model.FuelType;

import java.util.List;

/**
 * The interface of fuel type service.
 * Provides method to get list of fuel type models on the service layer
 *
 * @author Katuranau Maksimilyan
 */
public interface FuelTypeService {
    /**
     * Gets fuel types.
     *
     * @return the list of fuel types
     */
    List<FuelType> getFuelTypes();
}
