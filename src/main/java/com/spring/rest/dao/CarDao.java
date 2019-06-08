package com.spring.rest.dao;

import com.spring.rest.model.Car;

import java.util.List;

public interface CarDao {
    List<Car> getAllCars();
    Car getCar(int index);
    void addCar(int year, int mileage, int fuelTypeId, int transmissionId, int carModelId);
    void updateCar(int index, int mileage, int fuelTypeId, int transmissionId, int carModelId);
    void deleteCar(int index);
}
