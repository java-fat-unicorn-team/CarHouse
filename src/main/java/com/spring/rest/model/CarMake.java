package com.spring.rest.model;

/**
 * The Car make model.
 */
public class CarMake {
    /**
     * car make id.
     */
    private int carMakeId;
    /**
     * car make.
     */
    private String carMake;

    /**
     * Instantiates a new Car make.
     *
     * @param carMakeId the car make id
     * @param carMake   the car make
     */
    public CarMake(final int carMakeId, final String carMake) {
        this.carMakeId = carMakeId;
        this.carMake = carMake;
    }

    /**
     * Gets car make id.
     *
     * @return the car make id
     */
    public int getCarMakeId() {
        return carMakeId;
    }

    /**
     * Sets car make id.
     *
     * @param carMakeId the car make id
     */
    public void setCarMakeId(final int carMakeId) {
        this.carMakeId = carMakeId;
    }

    /**
     * Gets car make.
     *
     * @return the car make
     */
    public String getCarMake() {
        return carMake;
    }

    /**
     * Sets car make.
     *
     * @param carMake the car make
     */
    public void setCarMake(final String carMake) {
        this.carMake = carMake;
    }

    @Override
    public final String toString() {
        return "carMake='" + carMake + '\'';
    }
}
