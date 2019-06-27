package com.carhouse.model;

/**
 * The car feature model is used to describe feature.
 * The model includes feature description and car characteristics model which contains the feature.
 * One car can have more then one feature.
 * @author Katuranau Maksimilyan
 */
public class CarFeature {
    private int carFeatureId;
    private String carFeature;
    private Car car;

    /**
     * Instantiates a new Car feature.
     *
     * @param carFeatureId       the car feature id
     * @param carFeature         the car feature
     * @param car the car
     */
    public CarFeature(final int carFeatureId, final String carFeature, final Car car) {
        this.carFeatureId = carFeatureId;
        this.carFeature = carFeature;
        this.car = car;
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
     * @return the car which have this feature
     */
    public Car getCar() {
        return car;
    }

    /**
     * Sets car id.
     *
     * @param car the car id
     */
    public void setCar(final Car car) {
        this.car = car;
    }

    @Override
    public final String toString() {
        return "carFeature='" + carFeature + '\''
                + car;
    }
}
