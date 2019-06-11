package com.spring.rest.model;

/**
 * The Transmission model.
 */
public class Transmission {
    /**
     * transmission id.
     */
    private int transmissionId;
    /**
     * transmission's name.
     */
    private String transmission;

    /**
     * Instantiates a new Transmission.
     */
    public Transmission() {
    }

    /**
     * Instantiates a new Transmission.
     *
     * @param transmissionId the transmission id
     * @param transmission   the transmission
     */
    public Transmission(final int transmissionId, final String transmission) {
        this.transmissionId = transmissionId;
        this.transmission = transmission;
    }

    /**
     * Gets transmission id.
     *
     * @return the transmission id
     */
    public int getTransmissionId() {
        return transmissionId;
    }

    /**
     * Sets transmission id.
     *
     * @param transmissionId the transmission id
     */
    public void setTransmissionId(final int transmissionId) {
        this.transmissionId = transmissionId;
    }

    /**
     * Gets transmission.
     *
     * @return the transmission
     */
    public String getTransmission() {
        return transmission;
    }

    /**
     * Sets transmission.
     *
     * @param transmission the transmission
     */
    public void setTransmission(final String transmission) {
        this.transmission = transmission;
    }

    @Override
    public final String toString() {
        return "transmission='" + transmission + '\'';
    }
}
