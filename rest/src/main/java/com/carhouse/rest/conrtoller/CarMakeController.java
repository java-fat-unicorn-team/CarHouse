package com.carhouse.rest.conrtoller;

import com.carhouse.model.CarMake;
import com.carhouse.service.CarMakeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The Car make controller.
 * Provide endpoints to manage car make model
 *
 * @author Katuranau Maksimilyan
 */
@RequestMapping("/carSale/car/carModel/carMake")
@RestController
public class CarMakeController {

    private static final Logger LOGGER = LogManager.getLogger(CarMakeController.class);

    private CarMakeService carMakeService;

    /**
     * Instantiates a new Car make controller.
     *
     * @param carMakeService the car make service to manage car make object
     */
    @Autowired
    public CarMakeController(final CarMakeService carMakeService) {
        this.carMakeService = carMakeService;
    }

    /**
     * Send all car makes.
     *
     * @return the list of car makes in JSON
     */
    @GetMapping
    public List<CarMake> getCarMakes() {
        LOGGER.debug("method getCarMakes");
        return carMakeService.getCarMakes();
    }
}
