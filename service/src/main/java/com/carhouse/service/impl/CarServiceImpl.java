package com.carhouse.service.impl;

import com.carhouse.dao.CarDao;
import com.carhouse.model.Car;
import com.carhouse.service.CarService;
import com.carhouse.service.exception.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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

    private static final Logger LOGGER = LogManager.getLogger(CarServiceImpl.class);

    /**
     * Instantiates a new Car service.
     *
     * @param carDao the class is provided CRUD operations for fuel type model.
     */
    @Autowired
    public CarServiceImpl(final CarDao carDao) {
        this.carDao = carDao;
    }

    /**
     * Gets all cars from database.
     *
     * @return the list of cars
     */
    @Override
    public List<Car> getCars() {
        LOGGER.debug("method getCars");
        return carDao.getCars();
    }

    /**
     * Gets one car by id.
     *
     * @param id the car id
     * @return the car
     */
    @Override
    public Car getCar(final int id) {
        LOGGER.debug("method getCar with parameter: [{}]", id);
        try {
            return carDao.getCar(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException("there is not such car");
        }
    }

    /**
     * Add car.
     *
     * @param car the car model
     * @return car characteristic id
     */
    @Override
    public Integer addCar(final Car car) {
        LOGGER.debug("method addCar with parameter: [{}]", car);
        try {
            return carDao.addCar(car);
        } catch (DataIntegrityViolationException ex) {
            throw new WrongReferenceException("there is wrong references in your car");
        }
    }

    /**
     * Update car.
     * Gets car id from car object
     *
     * @param car the car model
     * @return check or car is updated
     */
    @Override
    public boolean updateCar(final Car car) {
        LOGGER.debug("method updateCar with parameter: [{}]", car);
        getCar(car.getCarId());
        try {
            return carDao.updateCar(car);
        } catch (DataIntegrityViolationException ex) {
            throw new WrongReferenceException("there is wrong references in your car");
        }
    }

    /**
     * Delete car by id.
     *
     * @param id the car id
     * @return check or car is deleted
     */
    @Override
    public boolean deleteCar(final int id) {
        LOGGER.debug("method deleteCar with parameter: [{}]", id);
        if (!carDao.deleteCar(id)) {
            throw new NotFoundException("car you are trying to delete does not exist");
        }
        return true;
    }
}
