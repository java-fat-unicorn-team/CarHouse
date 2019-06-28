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
     * Gets all cars from database
     *
     * @return the list of cars
     */
    List<Car> getCars();

    /**
     * Gets one car by id
     *
     * @param id the car id
     * @return the car
     */
     Car getCar(final int id);

    /**
     * Add car.
     *
     * @param car the car model
     * @return car characteristic id
     */
    Integer addCar(Car car);

    /**
     * Update car.
     * Gets car id from car object
     *
     * @param car the car model
     */
    void updateCar(Car car);

    /**
     * Delete car by id.
     *
     * @param id the car id
     */
    void deleteCar(final int id);
}
