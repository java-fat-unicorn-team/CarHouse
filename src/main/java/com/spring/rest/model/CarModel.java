package com.spring.rest.model;

public class CarModel {
    private int carModelId;
    private CarMake carMake;
    private String carModel;

    public CarModel() {
    }

    public CarModel(int carModelId, CarMake carMake, String carModel) {
        this.carModelId = carModelId;
        this.carMake = carMake;
        this.carModel = carModel;
    }

    public int getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(int carModelId) {
        this.carModelId = carModelId;
    }

    public CarMake getCarMake() {
        return carMake;
    }

    public void setCarMake(final CarMake pCarMake) {
        this.carMake = pCarMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(final String pCarModel) {
        this.carModel = pCarModel;
    }

    @Override
    public String toString() {
        return carMake + ", carModel='" + carModel + '\'';
    }
}
