package com.carhouse.rest.controller;

import com.carhouse.model.Transmission;
import com.carhouse.service.TransmissionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The Transmission com.carhouse.rest.controller.
 * Provide endpoints to manage transmission model
 *
 * @author Katuranau Maksimilyan
 */
@RequestMapping("carSale/car/transmission")
@RestController
public class TransmissionController {

    private static final Logger LOGGER = LogManager.getLogger(TransmissionController.class);

    private TransmissionService transmissionService;

    /**
     * Instantiates a new Transmission com.carhouse.rest.controller.
     *
     * @param transmissionService the transmission service to manage transmission object
     */
    @Autowired
    public TransmissionController(final TransmissionService transmissionService) {
        this.transmissionService = transmissionService;
    }

    /**
     * Send all transmissions.
     *
     * @return the list of transmissions in JSON.
     */
    @GetMapping
    public List<Transmission> getTransmissions() {
        LOGGER.debug("method getTransmissions");
        return transmissionService.getTransmissions();
    }
}
