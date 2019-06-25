package com.carhouse.dao;

import com.carhouse.model.Transmission;

import java.util.List;

/**
 * The interface provides methods to manage Transmission model.
 * @author Katuranau Maksimilyan
 * @see Transmission
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
