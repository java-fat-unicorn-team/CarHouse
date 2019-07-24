package com.carhouse.service.impl;

import com.carhouse.dao.TransmissionDao;
import com.carhouse.model.Transmission;
import com.carhouse.service.TransmissionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The class provides method to get Transmission models on service layer.
 * It is realisation of TransmissionService interface
 *
 * @author Katuranau Maksimilyan
 * @see TransmissionService
 */
@Service
public class TransmissionServiceImpl implements TransmissionService {

    private TransmissionDao transmissionDao;

    private static final Logger LOGGER = LogManager.getLogger(TransmissionServiceImpl.class);

    /**
     * Instantiates a new Transmission service.
     *
     * @param transmissionDao the class is provided CRUD operations for transmission model.
     */
    @Autowired
    public TransmissionServiceImpl(final TransmissionDao transmissionDao) {
        this.transmissionDao = transmissionDao;
    }

    /**
     * Gets transmissions.
     *
     * @return list of transmissions
     */
    @Override
    public List<Transmission> getTransmissions() {
        LOGGER.debug("method getTransmissions");
        return transmissionDao.getTransmissions();
    }

    /**
     * Gets transmission by id.
     *
     * @param id the transmission id.
     * @return transmission with selected id
     */
    @Override
    public Transmission getTransmission(final int id) {
        LOGGER.debug("method getTransmission with parameter: [{}]", id);
        return transmissionDao.getTransmission(id);
    }
}
