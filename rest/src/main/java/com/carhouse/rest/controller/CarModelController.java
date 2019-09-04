package com.carhouse.rest.controller;

import com.carhouse.model.CarModel;
import com.carhouse.service.CarModelService;
import javassist.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The Car model controller.
 * Provide endpoints to manage car model model
 *
 * @author Katuranau Maksimilyan
 */
@RequestMapping("/carSale/car/carModel")
@RestController
public class CarModelController {

    private static final Logger LOGGER = LogManager.getLogger(CarModelController.class);

    private CarModelService carModelService;

    /**
     * Instantiates a new Car model controller.
     *
     * @param carModelService the car model service to manage car model object
     */
    @Autowired
    public CarModelController(final CarModelService carModelService) {
        this.carModelService = carModelService;
    }

    /**
     * Send list car models of selected car make.
     *
     * @param carMakeId the car make id to get car models
     * @return the list of car models in JSON
     * @throws NotFoundException if there is not car make with selected id
     */
    @GetMapping("/list/{carMakeId}")
    public List<CarModel> getListCarModels(@PathVariable final Integer carMakeId) throws NotFoundException {
        LOGGER.debug("method getListCarModels wit parameter: [{}]", carMakeId);
        return carModelService.getCarModels(carMakeId);
    }

    /**
     * Send car model with selected id.
     *
     * @param carModelId id to get car model
     * @return car model
     * @throws NotFoundException if there is not car model with selected id
     */
    @GetMapping("/{carModelId}")
    public CarModel getCarModel(@PathVariable final Integer carModelId) throws NotFoundException {
        LOGGER.debug("method getCarModel wit parameter: [{}]", carModelId);
        return carModelService.getCarModel(carModelId);
    }
}
