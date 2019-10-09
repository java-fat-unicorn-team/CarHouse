package com.carhouse.rest.controller;

import com.carhouse.model.Car;
import com.carhouse.service.CarService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

/**
 * The Car controller.
 * Provide endpoints to manage car model
 *
 * @author Katuranau Maksimilyan
 */
@RequestMapping("/carSale/car")
@RestController
@Validated
public class CarController {

    private static final Logger LOGGER = LogManager.getLogger(CarController.class);

    private CarService carService;

    /**
     * Instantiates a new Car controller.
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
    @ApiOperation("get list of cars")
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
     * @throws NotFoundException throws if there is not such car
     */
    @ApiOperation("get car by id")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping("/{carId}")
    public Car getCar(@PathVariable
                      @PositiveOrZero(message = "car id can't be negative") final int carId)
            throws NotFoundException {
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
    @ApiOperation("add car, the year should be in this format \"yyyy-MM-dd\" or integer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK, returns id of created car"),
            @ApiResponse(code = 424, message = "Wrong References")})
    @PostMapping
    public Integer addCar(@RequestBody @Valid final Car car) {
        LOGGER.debug("method addCar wit parameter: [{}]", car);
        return carService.addCar(car);
    }

    /**
     * Update car.
     * Gets car object as request body.
     * Replace car with id provided in new car on this new car
     *
     * @param car the new car to update
     * @throws NotFoundException throws if there is not such car to update
     */
    @ApiOperation("update car sale, gets car sale id to update from object provided as request body\n"
            + "the date can be in integer or in this format \"yyyy-MM-dd\"")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 424, message = "Wrong References")})
    @PutMapping
    public void updateCar(@RequestBody @Valid final Car car) throws NotFoundException {
        LOGGER.debug("method updateCar wit parameter: [{}]", car);
        carService.updateCar(car);
    }

    /**
     * Delete car by id.
     * Gets car's id to delete as path variable
     *
     * @param carId the car id
     * @throws NotFoundException throws if there is not such car to delete
     */
    @ApiOperation("delete car by id")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 424, message = "Car Has References")})
    @DeleteMapping("/{carId}")
    public void deleteCar(@PathVariable
                          @PositiveOrZero(message = "car id can't be negative") final int carId)
            throws NotFoundException {
        LOGGER.debug("method deleteCar wit parameter: [{}]", carId);
        carService.deleteCar(carId);
    }
}
