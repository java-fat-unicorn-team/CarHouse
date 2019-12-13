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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.PositiveOrZero;
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
@Validated
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
    @ApiOperation("get car sales")
    @GetMapping
    public List<CarSaleDto> getCarSales(@RequestParam(required = false) final Map<String, String> requestParams) {
        LOGGER.debug("method getCarSalesDto");
        return carSaleService.getListCarSales(requestParams);
    }

    /**
     * Send last five car sales without redundant information to show list of them.
     *
     * @return the list of car sales in JSON
     */
    @ApiOperation("get last five car sales")
    @GetMapping("/last")
    public List<CarSaleDto> getLastFiveCarSales() {
        LOGGER.debug("method getCarSalesDto");
        return carSaleService.getListLastFiveCarSales();
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
    public CarSale getCarSale(@PathVariable
                              @PositiveOrZero(message = "car sale id can't be negative") final int carSaleId)
            throws NotFoundException {
        LOGGER.debug("method getCarSale wit parameter: [{}]", carSaleId);
        return carSaleService.getCarSale(carSaleId);
    }
}
