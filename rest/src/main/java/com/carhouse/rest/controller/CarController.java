package com.carhouse.rest.controller;

import com.carhouse.model.Car;
import com.carhouse.service.CarService;
import javassist.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The Car rest com.carhouse.rest.com.carhouse.rest.controller.
 * Provide endpoints to manage car model
 *
 * @author Katuranau Maksimilyan
 */
@RequestMapping("/carSale/car")
@RestController
public class CarController {

    private static final Logger LOGGER = LogManager.getLogger(CarController.class);

    private CarService carService;

    /**
     * Instantiates a new Car com.carhouse.rest.com.carhouse.rest.controller.
     *
     * @param carService the car service to manage car object
     */
    @Autowired
    public CarController(final CarService carService) {
        this.carService = carService;
    }

    /**
     * Send all cars.
     *
     * @return the list of cars
     */
    @GetMapping
    public List<Car> getCars() {
        LOGGER.debug("method getCars");
        return carService.getCars();
    }

    /**
     * Send car with selected id.
     *
     * @param carId the car id
     * @return the car with selected id
     */
    @GetMapping("/{carId}")
    public Car getCar(@PathVariable final int carId) throws NotFoundException {
        LOGGER.debug("method getCar wit parameter: [{}]", carId);
        return carService.getCar(carId);
    }

    /**
     * Add new car.
     * Gets car as request body.
     *
     * @param car the new car object to add
     * @return the id of added car
     */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Integer addCar(@RequestBody final Car car) {
        LOGGER.debug("method addCar wit parameter: [{}]", car);
        return carService.addCar(car);
    }

    /**
     * Update car.
     * Gets car object as request body.
     * Replace car with id provided in new car on this new car
     *
     * @param car the new car to update
     */
    @PutMapping
    public void updateCar(@RequestBody final Car car) throws NotFoundException {
        LOGGER.debug("method updateCar wit parameter: [{}]", car);
        carService.updateCar(car);
    }

    /**
     * Delete car by id.
     * Gets car's id to delete as path variable
     *
     * @param carId the car id
     */
    @DeleteMapping("/{carId}")
    public void deleteCar(@PathVariable final int carId) throws NotFoundException {
        LOGGER.debug("method deleteCar wit parameter: [{}]", carId);
        carService.deleteCar(carId);
    }
}
