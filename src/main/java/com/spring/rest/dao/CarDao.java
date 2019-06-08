package com.spring.rest.dao;

import com.spring.rest.model.Car;

import java.util.List;

/**
 * The interface Car dao.
 */
public interface CarDao {
    /**
     * Gets all cars.
     *
     * @return the all cars
     */
    List<Car> getAllCars();

    /**
     * Gets car.
     *
     * @param index the index
     * @return the car
     */
    Car getCar(int index);

    /**
     * Add car.
     *
     * @param year           the year
     * @param mileage        the mileage
     * @param fuelTypeId     the fuel type id
     * @param transmissionId the transmission id
     * @param carModelId     the car model id
     */
    void addCar(int year, int mileage, int fuelTypeId, int transmissionId,
                int carModelId);

    /**
     * Update car.
     *
     * @param index          the index
     * @param year           the year
     * @param mileage        the mileage
     * @param fuelTypeId     the fuel type id
     * @param transmissionId the transmission id
     * @param carModelId     the car model id
     */
    void updateCar(int index, int year, int mileage, int fuelTypeId,
                   int transmissionId, int carModelId);

    /**
     * Delete car.
     *
     * @param index the index
     */
    void deleteCar(int index);
}
