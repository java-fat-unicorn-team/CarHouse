package com.spring.rest.dao;

import com.spring.rest.model.Transmission;

import java.util.List;

/**
 * The interface provides methods to manage Transmission model.
 */
public interface TransmissionDao {
    /**
     * Gets transmissions.
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
