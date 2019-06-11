package com.spring.rest.dao;

import com.spring.rest.model.Transmission;

import java.util.List;

/**
 * The interface provides methods to get Transmission model.
 */
public interface TransmissionDao {
    /**
     * Gets all transmission.
     *
     * @return the all transmission
     */
    List<Transmission> getTransmissions();

    /**
     * Gets transmission.
     *
     * @param index the index
     * @return the transmission
     */
    Transmission getTransmission(int index);
}
