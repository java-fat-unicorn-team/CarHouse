package com.spring.rest.model;

/**
 * The type Car feature.
 */
public class CarFeature {
    private int carFeatureId;
    private String carFeature;
    private int carId;

    /**
     * Instantiates a new Car feature.
     *
     * @param carFeatureId the car feature id
     * @param carFeature   the car feature
     * @param carId        the car id
     */
    public CarFeature(final int carFeatureId, final String carFeature,
                      final int carId) {
        this.carFeatureId = carFeatureId;
        this.carFeature = carFeature;
        this.carId = carId;
    }

    /**
     * Gets car feature id.
     *
     * @return the car feature id
     */
    public int getCarFeatureId() {
        return carFeatureId;
    }

    /**
     * Sets car feature id.
     *
     * @param carFeatureId the car feature id
     */
    public void setCarFeatureId(final int carFeatureId) {
        this.carFeatureId = carFeatureId;
    }

    /**
     * Gets car feature.
     *
     * @return the car feature
     */
    public String getCarFeature() {
        return carFeature;
    }

    /**
     * Sets car feature.
     *
     * @param carFeature the car feature
     */
    public void setCarFeature(final String carFeature) {
        this.carFeature = carFeature;
    }

    /**
     * Gets car id.
     *
     * @return the car id
     */
    public int getCarId() {
        return carId;
    }

    /**
     * Sets car id.
     *
     * @param carId the car id
     */
    public void setCarId(final int carId) {
        this.carId = carId;
    }

    @Override
    public final String toString() {
        return "carFeature='" + carFeature + '\''
                + ", carId=" + carId;
    }
}
