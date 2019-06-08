package com.spring.rest.model;

public class CarMake {
    private int carMakeId;
    private String carMake;

    public CarMake() {
    }

    public CarMake(int carMakeId, String carMake) {
        this.carMakeId = carMakeId;
        this.carMake = carMake;
    }

    public int getCarMakeId() {
        return carMakeId;
    }

    public void setCarMakeId(int carMakeId) {
        this.carMakeId = carMakeId;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(final String pCarMake) {
        this.carMake = pCarMake;
    }

    @Override
    public String toString() {
        return "carMake='" + carMake + '\'';
    }
}
