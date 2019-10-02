package com.carhouse.service;

/**
 * The interface Car characteristics dto service.
 */
public interface CarCharacteristicsDtoService {

    /**
     * Gets car characteristics.
     * It is used to reduce the count of sql queries
     *
     * @return the car characteristics in json format
     */
    String getCarCharacteristics();
}
