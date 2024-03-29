package com.carhouse.rest.controller;

import com.carhouse.model.CarMake;
import com.carhouse.service.CarMakeService;
import javassist.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.PositiveOrZero;
import java.util.List;

/**
 * The Car controller.
 * Provide endpoints to manage car make model
 *
 * @author Katuranau Maksimilyan
 */
@RequestMapping("/carSale/car/carModel/carMake")
@RestController
@Validated
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

    /**
     * Send car make with selected id.
     *
     * @param carMakeId id to get car make
     * @return car make
     * @throws NotFoundException if there is not car make with selected id
     */
    @GetMapping("/{carMakeId}")
    public CarMake getCarMake(@PathVariable
                              @PositiveOrZero(message = "car make id can't be negative") final Integer carMakeId)
            throws NotFoundException {
        LOGGER.debug("method getCarMake wit parameter: [{}]", carMakeId);
        return carMakeService.getCarMake(carMakeId);
    }
}
