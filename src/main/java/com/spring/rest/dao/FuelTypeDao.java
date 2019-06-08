package com.spring.rest.dao;

import com.spring.rest.model.FuelType;

import java.util.List;

public interface FuelTypeDao {
    List<FuelType> getAllFuelTypes();
    FuelType getFuelType(int index);
    void addFuelType(String fuelType);
    void deleteFuelType(int index);
    void updateFuelType(int index, String fuelType);
}
