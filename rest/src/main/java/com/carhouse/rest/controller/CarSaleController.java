package com.carhouse.rest.controller;

import com.carhouse.model.CarSale;
import com.carhouse.model.dto.CarSaleDto;
import com.carhouse.service.CarSaleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * The Car sale controller.
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
     * Instantiates a new Car sale controller.
     *
     * @param carSaleService the car sale service to manage car sale object
     */
    @Autowired
    public CarSaleController(final CarSaleService carSaleService) {
        this.carSaleService = carSaleService;
    }

    /**
     * Send all car sales without redundant information to show list of them.
     *
     * @param requestParams the request params
     * @return the list of car sales in JSON
     */
    @GetMapping
    public List<CarSaleDto> getCarSales(@RequestParam(required = false) final Map<String, String> requestParams) {
        LOGGER.debug("method getCarSalesDto");
        return carSaleService.getListCarSales(requestParams);
    }

    /**
     * Send car sale with provided id.
     * Get car sale id as path variable
     *
     * @param carSaleId the car sale id
     * @return the car sale with provided id
     * @throws NotFoundException throws if there is not such car sale
     */
    @ApiOperation("get car sale by id")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping("/{carSaleId}")
    public CarSale getCarSale(@PathVariable final int carSaleId) throws NotFoundException {
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
    @ApiOperation("add car sale, the date should be in this format \"yyyy-MM-dd HH:mm:ss\" or in integer")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 424, message = "Wrong References")})
    @PostMapping
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
     * @throws NotFoundException throws if there is not such car sale to update
     */
    @ApiOperation("update car sale, gets car sale id to update from object provided as request body\n"
            + "the date can be in integer or in this format \"yyyy-MM-dd HH:mm:ss\"")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 424, message = "Wrong References")})
    @PutMapping
    public void updateCarSale(@RequestBody final CarSale carSale) throws NotFoundException {
        LOGGER.debug("method updateCarSale wit parameter: [{}]", carSale);
        carSaleService.updateCarSale(carSale);
    }

    /**
     * Delete car sale by id.
     * Get car sale's id to delete as path variable
     *
     * @param carSaleId the car sale id
     * @throws NotFoundException throws if there is not such car sale to delete
     */
    @ApiOperation("delete car sale by id")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 424, message = "Car Has References")})
    @DeleteMapping("/{carSaleId}")
    public void deleteCarSale(@PathVariable final int carSaleId) throws NotFoundException {
        LOGGER.debug("method deleteCarSale wit parameter: [{}]", carSaleId);
        carSaleService.deleteCarSale(carSaleId);
    }
}
