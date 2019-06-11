package com.spring.rest.model;

/**
 * The Car model model.
 */
public class CarModel {
    /**
     * car model id.
     */
    private int carModelId;
    /**
     * car make.
     */
    private CarMake carMake;
    /**
     * car model.
     */
    private String carModel;

    /**
     * Instantiates a new Car model.
     */
    public CarModel() {
    }

    /**
     * Instantiates a new Car model.
     *
     * @param carModelId the car model id
     * @param carMake    the car make
     * @param carModel   the car model
     */
    public CarModel(final int carModelId, final CarMake carMake,
                    final String carModel) {
        this.carModelId = carModelId;
        this.carMake = carMake;
        this.carModel = carModel;
    }

    /**
     * Gets car model id.
     *
     * @return the car model id
     */
    public int getCarModelId() {
        return carModelId;
    }

    /**
     * Sets car model id.
     *
     * @param carModelId the car model id
     */
    public void setCarModelId(final int carModelId) {
        this.carModelId = carModelId;
    }

    /**
     * Gets car make.
     *
     * @return the car make
     */
    public CarMake getCarMake() {
        return carMake;
    }

    /**
     * Sets car make.
     *
     * @param carMake the car make
     */
    public void setCarMake(final CarMake carMake) {
        this.carMake = carMake;
    }

    /**
     * Gets car model.
     *
     * @return the car model
     */
    public String getCarModel() {
        return carModel;
    }

    /**
     * Sets car model.
     *
     * @param carModel the car model
     */
    public void setCarModel(final String carModel) {
        this.carModel = carModel;
    }

    @Override
    public final String toString() {
        return carMake + carModel;
    }
}
