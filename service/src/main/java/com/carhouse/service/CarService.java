package com.carhouse.service;

import com.carhouse.model.Car;
import javassist.NotFoundException;

import java.util.List;

/**
 * The interface of car service.
 * provides methods to manage Car model.
 *
 * @author Katuranau Maksimilyan
 * @see Car
 */
public interface CarService {
    /**
     * Gets all cars from database.
     *
     * @return the list of cars
     */
    List<Car> getCars();

    /**
     * Gets one car by id.
     *
     * @param id the car id
     * @return the car
     * @throws NotFoundException throws if there is not such car
     */
    Car getCar(int id) throws NotFoundException;

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
     * @throws NotFoundException throws if there is not such car to update
     */
    void updateCar(Car car) throws NotFoundException;

    /**
     * Delete car by id.
     *
     * @param id the car id
     * @throws NotFoundException throws if there is not such car to delete
     */
    void deleteCar(int id) throws NotFoundException;
}
