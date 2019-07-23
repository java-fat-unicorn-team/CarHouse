package com.carhouse.service.impl;

import com.carhouse.dao.CarDao;
import com.carhouse.model.Car;
import com.carhouse.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The class provides method to manage car models on service layer.
 * It is realisation of CarService interface
 *
 * @author Katuranau Maksimilyan
 * @see CarService
 */
@Service
public class CarServiceImpl implements CarService {

    private CarDao carDao;

    /**
     * Instantiates a new Car service.
     *
     * @param carDao the class is provided CRUD operations for fuel type model.
     */
    @Autowired
    public CarServiceImpl(CarDao carDao) {
        this.carDao = carDao;
    }

    /**
     * Gets all cars from database.
     *
     * @return the list of cars
     */
    @Override
    public List<Car> getCars() {
        return carDao.getCars();
    }

    /**
     * Gets one car by id.
     *
     * @param id the car id
     * @return the car
     */
    @Override
    public Car getCar(int id) {
        return carDao.getCar(id);
    }

    /**
     * Add car.
     *
     * @param car the car model
     * @return car characteristic id
     */
    @Override
    public Integer addCar(Car car) {
        return carDao.addCar(car);
    }

    /**
     * Update car.
     * Gets car id from car object
     *
     * @param car the car model
     */
    @Override
    public void updateCar(Car car) {
        carDao.updateCar(car);
    }

    /**
     * Delete car by id.
     *
     * @param id the car id
     */
    @Override
    public void deleteCar(int id) {
        carDao.deleteCar(id);
    }
}
