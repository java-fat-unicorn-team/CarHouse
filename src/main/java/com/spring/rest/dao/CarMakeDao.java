package com.spring.rest.dao;

import com.spring.rest.model.CarMake;

import java.util.List;

public interface CarMakeDao {
    List<CarMake> getAllCarMakes();
    CarMake getCarMake(int index);
}
