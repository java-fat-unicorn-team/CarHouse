package com.carhouse.rest.conrtoller;

import com.carhouse.model.FuelType;
import com.carhouse.service.FuelTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The Fuel type controller.
 * It is rest controller which sends data as JSON.
 */
@RequestMapping("/carSale/car/fuelType")
@RestController
public class FuelTypeController {

    private FuelTypeService fuelTypeService;

    /**
     * Instantiates a new Fuel type controller.
     * Gets fuelTypeService object which provides methods to manage fuel type as parameter
     *
     * @param fuelTypeService the fuel type service
     */
    @Autowired
    FuelTypeController(final FuelTypeService fuelTypeService) {
        this.fuelTypeService = fuelTypeService;
    }

    /**
     * Send to browser all fuel types.
     *
     * @return the list of fuel types in JSON.
     */
    @GetMapping
    public List<FuelType> getFuelTypes() {
        return fuelTypeService.getFuelTypes();
    }
}
