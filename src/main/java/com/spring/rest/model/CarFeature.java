package com.spring.rest.model;

public class CarFeature {
    private int carFeatureId;
    private String carFeature;
    private int carId;

    public CarFeature(int carFeatureId, String carFeature, int carId) {
        this.carFeatureId = carFeatureId;
        this.carFeature = carFeature;
        this.carId = carId;
    }

    public int getCarFeatureId() {
        return carFeatureId;
    }

    public void setCarFeatureId(int carFeatureId) {
        this.carFeatureId = carFeatureId;
    }

    public String getCarFeature() {
        return carFeature;
    }

    public void setCarFeature(String carFeature) {
        this.carFeature = carFeature;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    @Override
    public String toString() {
        return "carFeature='" + carFeature + '\'' +
                ", carId=" + carId;
    }
}
