package com.spring.rest.model;

/**
 * The car feature model is used to describe feature.
 * The model includes feature description and car characteristics model which contains the feature.
 * One car can have more then one feature.
 * @author Katuranau Maksimilyan
 */
public class CarFeature {
    private int carFeatureId;
    private String carFeature;
    private CarCharacteristics carCharacteristics;

    /**
     * Instantiates a new Car feature.
     *
     * @param carFeatureId       the car feature id
     * @param carFeature         the car feature
     * @param carCharacteristics the car
     */
    public CarFeature(final int carFeatureId, final String carFeature, final CarCharacteristics carCharacteristics) {
        this.carFeatureId = carFeatureId;
        this.carFeature = carFeature;
        this.carCharacteristics = carCharacteristics;
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
    public CarCharacteristics getCarCharacteristics() {
        return carCharacteristics;
    }

    /**
     * Sets car id.
     *
     * @param carCharacteristics the car id
     */
    public void setCarCharacteristics(final CarCharacteristics carCharacteristics) {
        this.carCharacteristics = carCharacteristics;
    }

    @Override
    public final String toString() {
        return "carFeature='" + carFeature + '\''
                + carCharacteristics;
    }
}
