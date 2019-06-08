package com.spring.rest.dao;

import com.spring.rest.model.CarFeature;

import java.util.List;

public interface CarFeatureDao {
    List<CarFeature> getAllCarFeatures(int carId);

    CarFeature getCarFeature(int index);

    void addCarFeature(String carFeature, int carId);

    void updateCarFeature(String carFeature, int carId);

    void deleteCarFeature(int index);
}
