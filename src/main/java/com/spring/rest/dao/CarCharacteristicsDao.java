package com.spring.rest.dao;

import com.spring.rest.model.CarCharacteristics;

import java.util.List;

/**
 * The interface provides methods to manage CarCharacteristics model.
 */
public interface CarCharacteristicsDao {
    /**
     * Gets cars.
     *
     * @return the list of cars
     */
    List<CarCharacteristics> getCarsCharacteristics();

    /**
     * Gets car.
     *
     * @param index the index
     * @return the car
     */
    CarCharacteristics getCarCharacteristics(int index);

    /**
     * Add car characteristics.
     *
     * @param car the car
     */
    void addCarCharacteristics(CarCharacteristics car);

    /**
     * Update car characteristics.
     *
     * @param car the car
     */
    void updateCarCharacteristics(CarCharacteristics car);

    /**
     * Delete car.
     *
     * @param index the index
     */
    void deleteCarCharacteristics(int index);
}
