package com.carhouse.dao;

/**
 * The interface Car characteristics dto dao.
 */
public interface CarCharacteristicsDtoDao {

    /**
     * Gets car characteristics.
     * It is used to reduce the count of sql queries
     *
     * @return the car characteristics in json format
     */
    String getCarCharacteristics();
}
