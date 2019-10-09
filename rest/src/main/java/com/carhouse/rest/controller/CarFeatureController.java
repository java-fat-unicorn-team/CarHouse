package com.carhouse.rest.controller;

import com.carhouse.model.CarFeature;
import com.carhouse.service.CarFeatureService;
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
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.PositiveOrZero;
import java.util.List;

/**
 * The Car feature controller.
 * Provide endpoints to manage car feature model
 *
 * @author Katuranau Maksimilyan
 */
@RequestMapping("/carSale/car")
@RestController
@Validated
public class CarFeatureController {

    private static final Logger LOGGER = LogManager.getLogger(CarFeatureController.class);

    private CarFeatureService carFeatureService;

    /**
     * Instantiates a new Car feature controller.
     *
     * @param carFeatureService the car feature service to manage car feature object
     */
    @Autowired
    public CarFeatureController(final CarFeatureService carFeatureService) {
        this.carFeatureService = carFeatureService;
    }

    /**
     * Send all car features.
     *
     * @param carId the car id
     * @return the car features
     * @throws NotFoundException throws if there is not such car to get features
     */
    @ApiOperation("get feature list of car with provided id")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping("/{carId}/carFeature")
    public List<CarFeature> getCarFeatures(@PathVariable
                                           @PositiveOrZero(message = "car feature id cant be negative") final int carId)
            throws NotFoundException {
        return carFeatureService.getCarFeatures(carId);
    }

    /**
     * Send all possible features.
     *
     * @return the list of features
     */
    @ApiOperation("get all possible features")
    @GetMapping("/carFeature")
    public List<CarFeature> getAllFeatures() {
        LOGGER.debug("method getAllFeatures");
        return carFeatureService.getAllFeatures();
    }
}
