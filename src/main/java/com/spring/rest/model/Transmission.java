package com.spring.rest.model;

public class Transmission {
    private int transmissionId;
    private String transmission;

    public Transmission() {
    }

    public Transmission(int transmissionId, String transmission) {
        this.transmissionId = transmissionId;
        this.transmission = transmission;
    }

    public int getTransmissionId() {
        return transmissionId;
    }

    public void setTransmissionId(int transmissionId) {
        this.transmissionId = transmissionId;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(final String pTransmission) {
        this.transmission = transmission;
    }

    @Override
    public String toString() {
        return "transmission='" + transmission + '\'';
    }
}
