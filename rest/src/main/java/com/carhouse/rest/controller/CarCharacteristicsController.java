package com.carhouse.rest.controller;

import com.carhouse.model.dto.CarCharacteristicsDto;
import com.carhouse.service.CarCharacteristicsDtoService;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Car characteristics controller.
 * Provide endpoint to get car characteristics dto model
 *
 * @author Katuranau Maksimilyan
 */
@RestController
public class CarCharacteristicsController {

    private static final Logger LOGGER = LogManager.getLogger(CarCharacteristicsController.class);

    private CarCharacteristicsDtoService carCharacteristicsDtoService;

    /**
     * Instantiates a new Car characteristics controller.
     *
     * @param carCharacteristicsDtoService the car characteristics dto service
     */
    @Autowired
    public CarCharacteristicsController(final CarCharacteristicsDtoService carCharacteristicsDtoService) {
        this.carCharacteristicsDtoService = carCharacteristicsDtoService;
    }

    /**
     * Gets car characteristics dto.
     * It is used to reduce the count of calls to the server from client
     *
     * @return the car characteristics dto
     */
    @ApiOperation("get car characteristics dto")
    @GetMapping("/carCharacteristics")
    public CarCharacteristicsDto getCarCharacteristicsDto() {
        LOGGER.debug("method getCarCharacteristicsDto");
        return carCharacteristicsDtoService.getCarCharacteristics();
    }
}
