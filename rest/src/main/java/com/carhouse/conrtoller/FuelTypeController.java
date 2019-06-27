package com.carhouse.conrtoller;

import com.carhouse.model.FuelType;
import com.carhouse.service.FuelTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The Fuel type controller.
 * It is rest controller which sends data as JSON.
 */
@RestController
public class FuelTypeController {

    private FuelTypeService fuelTypeService;

    /**
     * Instantiates a new Fuel type controller.
     *
     * @param fuelTypeService the fuel type service
     */
    @Autowired
    FuelTypeController(FuelTypeService fuelTypeService) {
        this.fuelTypeService = fuelTypeService;
    }

    /**
     * Send to browser all fuel types.
     *
     * @return the list of fuel types in JSON.
     */
    @GetMapping("/fuelType/all")
    public List<FuelType> getFuelTypes() {
        return fuelTypeService.getFuelTypes();
    }

    /**
     * It is a temprorary method to set welcome page and then easier call another method.
     *
     * @return the link
     */
    @GetMapping
    public String getLink() {
        return "<a href='/fuelType/all'>show all fuel types</a>";
    }
}
