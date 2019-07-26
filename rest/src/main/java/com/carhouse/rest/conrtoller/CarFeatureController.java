package com.carhouse.rest.conrtoller;

import com.carhouse.model.CarFeature;
import com.carhouse.service.CarFeatureService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The Car feature controller.
 * It is rest controller which sends data as JSON.
 *
 * @author Katuranau Maksimilyan
 */
@RequestMapping("/carSale/car")
@RestController
public class CarFeatureController {

    private CarFeatureService carFeatureService;

    private static final Logger LOGGER = LogManager.getLogger(CarFeatureController.class);

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
     */
    @GetMapping("/{carId}/carFeature")
    public List<CarFeature> getCarFeatures(@PathVariable final int carId) {
        return carFeatureService.getCarFeatures(carId);
    }

    /**
     * Send all possible features.
     *
     * @return the list of features
     */
    @GetMapping("/carFeature")
    public List<CarFeature> getAllFeatures() {
        LOGGER.debug("method getAllFeatures");
        return carFeatureService.getAllFeatures();
    }
}
