package com.carhouse.rest.controller;

import com.carhouse.model.CarSale;
import com.carhouse.service.CarSaleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The Car sale com.carhouse.rest.controller.
 * Provide endpoints to manage car sale model
 *
 * @author Katuranau Maksimilyan
 */
@RequestMapping("/carSale")
@RestController
public class CarSaleController {

    private static final Logger LOGGER = LogManager.getLogger(CarSaleController.class);

    private CarSaleService carSaleService;

    /**
     * Instantiates a new Car sale com.carhouse.rest.controller.
     *
     * @param carSaleService the car sale service to manage car sale object
     */
    @Autowired
    public CarSaleController(final CarSaleService carSaleService) {
        this.carSaleService = carSaleService;
    }

    /**
     * Send all car sales.
     *
     * @return the list of car sales in JSON
     */
    @GetMapping
    public List<CarSale> getCarSales() {
        LOGGER.debug("method getCarSales");
        return carSaleService.getCarSales();
    }

    /**
     * Send car sale with provided id.
     * Get car sale id as path variable
     *
     * @param carSaleId the car sale id
     * @return the car sale with provided id
     */
    @GetMapping("/{carSaleId}")
    public CarSale getCarSale(@PathVariable final int carSaleId) {
        LOGGER.debug("method getCarSale wit parameter: [{}]", carSaleId);
        return carSaleService.getCarSale(carSaleId);
    }

    /**
     * Add new car sale.
     * Get car sale object as request body
     * Car sale id is auto generated
     *
     * @param carSale the car sale object to add
     * @return the id generated for this object
     */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Integer addCarSale(@RequestBody final CarSale carSale) {
        LOGGER.debug("method addCarSale wit parameter: [{}]", carSale);
        return carSaleService.addCarSale(carSale);
    }

    /**
     * Update car sale.
     * Get car sale object as request body
     * Replace car sale with id provided in new car sale on this new object
     *
     * @param carSale the car sale object to update
     */
    @PutMapping
    public void updateCarSale(@RequestBody final CarSale carSale) {
        LOGGER.debug("method updateCarSale wit parameter: [{}]", carSale);
        carSaleService.updateCarSale(carSale);
    }

    /**
     * Delete car sale by id.
     * Get car sale's id to delete as path variable
     *
     * @param carSaleId the car sale id
     */
    @DeleteMapping("/{carSaleId}")
    public void deleteCarSale(@PathVariable final int carSaleId) {
        LOGGER.debug("method deleteCarSale wit parameter: [{}]", carSaleId);
        carSaleService.deleteCarSale(carSaleId);
    }
}
