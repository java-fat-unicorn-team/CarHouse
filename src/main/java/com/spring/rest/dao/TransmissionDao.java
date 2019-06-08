package com.spring.rest.dao;

import com.spring.rest.model.Transmission;

import java.util.List;

/**
 * The interface Transmission dao.
 */
public interface TransmissionDao {
    /**
     * Gets all transmission.
     *
     * @return the all transmission
     */
    List<Transmission> getAllTransmission();

    /**
     * Gets transmission.
     *
     * @param index the index
     * @return the transmission
     */
    Transmission getTransmission(int index);
}
