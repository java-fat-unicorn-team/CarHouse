package com.carhouse.dao;

import com.carhouse.model.Car;

import java.util.List;

/**
 * The interface provides methods to manage Car model.
 * @author Katuranau Maksimilyan
 * @see Car
 */
public interface CarDao {
    /**
     * Gets cars.
     *
     * @return the list of cars
     */
    List<Car> getCars();

    /**
     * Gets car.
     *
     * @param index the index
     * @return the car
     */
    Car getCar(int index);

    /**
     * Add car characteristics.
     *
     * @param car the car
     * @return car characteristic id
     */
    Integer addCar(Car car);

    /**
     * Update car characteristics.
     *
     * @param car the car
     */
    void updateCar(Car car);

    /**
     * Delete car.
     *
     * @param index the index
     */
    void deleteCar(int index);
}
