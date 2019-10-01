package com.carhouse.service;

import com.carhouse.model.dto.CarCharacteristicsDto;

/**
 * The interface Car characteristics dto service.
 */
public interface CarCharacteristicsDtoService {

    /**
     * Gets car characteristics.
     * It is used to reduce the count of sql queries
     *
     * @return the car characteristics
     */
    CarCharacteristicsDto getCarCharacteristics();
}
