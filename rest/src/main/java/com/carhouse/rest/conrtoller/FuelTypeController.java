package com.carhouse.rest.conrtoller;

import com.carhouse.model.FuelType;
import com.carhouse.service.FuelTypeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The Fuel type controller.
 * It is rest controller which sends data as JSON.
 *
 * @author Katuranau Maksimilyan
 */
@RequestMapping("/carSale/car/fuelType")
@RestController
public class FuelTypeController {

    private FuelTypeService fuelTypeService;

    private static final Logger LOGGER = LogManager.getLogger(FuelTypeController.class);

    /**
     * Instantiates a new Fuel type controller.
     *
     * @param fuelTypeService the fuel type service to manage fuel type object
     */
    @Autowired
    FuelTypeController(final FuelTypeService fuelTypeService) {
        this.fuelTypeService = fuelTypeService;
    }

    /**
     * Send all fuel types.
     *
     * @return the list of fuel types in JSON.
     */
    @GetMapping
    public List<FuelType> getFuelTypes() {
        LOGGER.debug("method getFuelTypes");
        return fuelTypeService.getFuelTypes();
    }
}
