package com.carhouse.model.dto;

import com.carhouse.model.CarFeature;
import com.carhouse.model.CarMake;
import com.carhouse.model.FuelType;
import com.carhouse.model.Transmission;

import java.util.List;

/**
 * The car characteristics dto.
 * This class is used to reduce the count of request to the server from the client
 * Contains list car makes, fuel types, transmissions and car features
 */
public class CarCharacteristicsDto {
    private List<CarMake> carMakeList;
    private List<FuelType> fuelTypeList;
    private List<Transmission> transmissionList;
    private List<CarFeature> carFeatureList;

    /**
     * Gets car make list.
     *
     * @return the car make list
     */
    public List<CarMake> getCarMakeList() {
        return carMakeList;
    }

    /**
     * Sets car make list.
     *
     * @param carMakeList the car make list
     */
    public void setCarMakeList(final List<CarMake> carMakeList) {
        this.carMakeList = carMakeList;
    }

    /**
     * Gets fuel type list.
     *
     * @return the fuel type list
     */
    public List<FuelType> getFuelTypeList() {
        return fuelTypeList;
    }

    /**
     * Sets fuel type list.
     *
     * @param fuelTypeList the fuel type list
     */
    public void setFuelTypeList(final List<FuelType> fuelTypeList) {
        this.fuelTypeList = fuelTypeList;
    }

    /**
     * Gets transmission list.
     *
     * @return the transmission list
     */
    public List<Transmission> getTransmissionList() {
        return transmissionList;
    }

    /**
     * Sets transmission list.
     *
     * @param transmissionList the transmission list
     */
    public void setTransmissionList(final List<Transmission> transmissionList) {
        this.transmissionList = transmissionList;
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
}
