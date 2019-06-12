package com.spring.rest.model;

/**
 * The Car feature model.
 */
public class CarFeature {
    /**
     * car feature id.
     */
    private int carFeatureId;
    /**
     * car feature.
     */
    private String carFeature;
    /**
     * car which have this feature.
     */
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
