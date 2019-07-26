package com.carhouse.rest.conrtoller;

import com.carhouse.model.CarModel;
import com.carhouse.service.CarModelService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The Car model controller.
 * It is rest controller which sends data as JSON.
 *
 * @author Katuranau Maksimilyan
 */
@RequestMapping("/carSale/car/carModel")
@RestController
public class CarModelController {

    private CarModelService carModelService;

    private static final Logger LOGGER = LogManager.getLogger(CarModelController.class);

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
     * Send all car models.
     *
     * @return the list of car models in JSON
     */
    @GetMapping
    public List<CarModel> getCarModels() {
        LOGGER.debug("method getCarModels");
        return carModelService.getCarModels();
    }
}
