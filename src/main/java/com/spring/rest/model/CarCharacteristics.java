package com.spring.rest.model;

/**
 * The car characteristics model.
 * This model includes year of car manufacture, car mileage, fuel type of car, transmission type and car model.
 * Each car includes this model.
 *
 * @author Katuranau Maksimilyan
 */
public class CarCharacteristics {
    private int carId;
    private int year;
    private int mileage;
    private FuelType fuelType;
    private Transmission transmission;
    private CarModel carModel;

    /**
     * Instantiates a new Car characteristics.
     *
     * @param carId the car id
     */
    public CarCharacteristics(final int carId) {
        this.carId = carId;
    }

    /**
     * Instantiates a new CarCharacteristics.
     *
     * @param carId        the car id
     * @param year         the year
     * @param mileage      the mileage
     * @param fuelType     the fuel type
     * @param transmission the transmission
     * @param carModel     the car model
     */
    public CarCharacteristics(final int carId, final int year, final int mileage, final FuelType fuelType,
                              final Transmission transmission, final CarModel carModel) {
        this.carId = carId;
        this.year = year;
        this.mileage = mileage;
        this.fuelType = fuelType;
        this.transmission = transmission;
        this.carModel = carModel;
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

    /**
     * Gets year.
     *
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets year.
     *
     * @param year the year
     */
    public void setYear(final int year) {
        this.year = year;
    }

    /**
     * Gets mileage.
     *
     * @return the mileage
     */
    public int getMileage() {
        return mileage;
    }

    /**
     * Sets mileage.
     *
     * @param mileage the mileage
     */
    public void setMileage(final int mileage) {
        this.mileage = mileage;
    }

    /**
     * Gets fuel type.
     *
     * @return the fuel type
     */
    public FuelType getFuelType() {
        return fuelType;
    }

    /**
     * Sets fuel type.
     *
     * @param fuelType the fuel type
     */
    public void setFuelType(final FuelType fuelType) {
        this.fuelType = fuelType;
    }

    /**
     * Gets transmission.
     *
     * @return the transmission
     */
    public Transmission getTransmission() {
        return transmission;
    }

    /**
     * Sets transmission.
     *
     * @param transmission the transmission
     */
    public void setTransmission(final Transmission transmission) {
        this.transmission = transmission;
    }

    /**
     * Gets car model.
     *
     * @return the car model
     */
    public CarModel getCarModel() {
        return carModel;
    }

    /**
     * Sets car model.
     *
     * @param carModel the car model
     */
    public void setCarModel(final CarModel carModel) {
        this.carModel = carModel;
    }

    @Override
    public final String toString() {
        return "carId=" + carId
                + ", year=" + year
                + ", mileage=" + mileage
                + ", " + fuelType
                + ", " + transmission
                + ", " + carModel
                + ';';
    }
}
