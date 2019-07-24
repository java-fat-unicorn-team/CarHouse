package com.carhouse.service;

import com.carhouse.model.Transmission;

import java.util.List;

/**
 * The interface of transmission service.
 * provides methods to get Transmission models.
 * @author Katuranau Maksimilyan
 * @see Transmission
 */
public interface TransmissionService {
    /**
     * Gets transmissions.
     *
     * @return the all transmission
     */
    List<Transmission> getTransmissions();

    /**
     * Gets transmission by id.
     *
     * @param id the transmission id.
     * @return the transmission
     */
    Transmission getTransmission(int id);
}
