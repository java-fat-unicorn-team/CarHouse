package com.carhouse.dao;

import com.carhouse.model.dto.CarCharacteristicsDto;

/**
 * The interface Car characteristics dto dao.
 */
public interface CarCharacteristicsDtoDao {

    /**
     * Gets car characteristics.
     * It is used to reduce the count of sql queries
     *
     * @return the car characteristics
     */
    CarCharacteristicsDto getCarCharacteristics();
}
