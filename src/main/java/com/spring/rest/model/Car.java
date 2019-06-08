package com.spring.rest.model;

import java.util.Date;

public class Car {
    private int carId;
    private Date year;
    private int mileage;
    private FuelType fuelType;
    private Transmission transmission;
    private CarModel carModel;


    public Car() {
    }

    public Car(int carId, Date year, int mileage, FuelType fuelType, Transmission transmission, CarModel carModel) {
        this.carId = carId;
        this.year = year;
        this.mileage = mileage;
        this.fuelType = fuelType;
        this.transmission = transmission;
        this.carModel = carModel;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(final Date pYear) {
        this.year = pYear;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(final int pMileage) {
        this.mileage = pMileage;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(final FuelType pFuelType) {
        this.fuelType = pFuelType;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(final Transmission pTransmission) {
        this.transmission = pTransmission;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(final CarModel pCarModel) {
        this.carModel = pCarModel;
    }

    @Override
    public String toString() {
        return "carId=" + carId +
                ", year=" + year.getYear() +
                ", mileage=" + mileage +
                ", " + fuelType +
                ", " + transmission +
                ", " + carModel +
                ';';
    }
}
