package com.carhouse.model;

import java.sql.Date;
import java.util.List;

/**
 * The car characteristics model.
 * This model includes year of car manufacture, car mileage, fuel type of car, transmission type and car model.
 * Each car includes this model.
 *
 * @author Katuranau Maksimilyan
 */
public class Car {
    private int carId;
    private Date year;
    private int mileage;
    private FuelType fuelType;
    private Transmission transmission;
    private CarModel carModel;
    private List<CarFeature> carFeatureList;

    /**
     * Instantiates a new Car characteristics.
     *
     * @param carId the car id
     */
    public Car(final int carId) {
        this.carId = carId;
    }

    /**
     * Instantiates a new Car.
     *
     * @param carId          the car id
     * @param year           the year
     * @param mileage        the mileage
     * @param fuelType       the fuel type
     * @param transmission   the transmission
     * @param carModel       the car model
     * @param carFeatureList the car feature list
     */
    public Car(final int carId, final Date year, final int mileage, final FuelType fuelType,
               final Transmission transmission, final CarModel carModel, final List<CarFeature> carFeatureList) {
        this.carId = carId;
        this.year = year;
        this.mileage = mileage;
        this.fuelType = fuelType;
        this.transmission = transmission;
        this.carModel = carModel;
        this.carFeatureList = carFeatureList;
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
    public Date getYear() {
        return year;
    }

    /**
     * Sets year.
     *
     * @param year the year
     */
    public void setYear(final Date year) {
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

    /**
     * Gets car feature list.
     *
     * @return the car feature list
     */
    public List<CarFeature> getCarFeatureList() {
        return carFeatureList;
    }

    /**
     * Sets car feature list.
     *
     * @param carFeatureList the car feature list
     */
    public void setCarFeatureList(final List<CarFeature> carFeatureList) {
        this.carFeatureList = carFeatureList;
    }

    @Override
    public final String toString() {
        return "carId=" + carId
                + ", year=" + year
                + ", mileage=" + mileage
                + ", " + fuelType
                + ", " + transmission
                + ", " + carModel
                + ", carFeatures = " + carFeatureList
                + ';';
    }
}
