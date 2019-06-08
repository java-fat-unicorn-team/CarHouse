package com.spring.rest.model;

/**
 * The type Fuel type.
 */
public class FuelType {
    private int fuelTypeId;
    private String fuelType;

    /**
     * Instantiates a new Fuel type.
     */
    public FuelType() {
    }

    /**
     * Instantiates a new Fuel type.
     *
     * @param fuelTypeId the fuel type id
     * @param fuelType   the fuel type
     */
    public FuelType(final int fuelTypeId, final String fuelType) {
        this.fuelTypeId = fuelTypeId;
        this.fuelType = fuelType;
    }

    /**
     * Gets fuel type id.
     *
     * @return the fuel type id
     */
    public int getFuelTypeId() {
        return fuelTypeId;
    }

    /**
     * Sets fuel type id.
     *
     * @param fuelTypeId the fuel type id
     */
    public void setFuelTypeId(final int fuelTypeId) {
        this.fuelTypeId = fuelTypeId;
    }

    /**
     * Gets fuel type.
     *
     * @return the fuel type
     */
    public String getFuelType() {
        return fuelType;
    }

    /**
     * Sets fuel type.
     *
     * @param fuelType the fuel type
     */
    public void setFuelType(final String fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public String toString() {
        return "fuelType='" + fuelType + '\'';
    }
}
