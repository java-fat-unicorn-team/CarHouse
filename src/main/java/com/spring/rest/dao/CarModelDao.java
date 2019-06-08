package com.spring.rest.dao;

import com.spring.rest.model.CarModel;

import java.util.List;

public interface CarModelDao {
    List<CarModel> getAllCarModels();

    CarModel getCarModel(int index);
}
