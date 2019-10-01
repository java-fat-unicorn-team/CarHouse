package com.carhouse.service.impl;

import com.carhouse.dao.CarCharacteristicsDtoDao;
import com.carhouse.model.dto.CarCharacteristicsDto;
import com.carhouse.service.CarCharacteristicsDtoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The class provides method to get CarCharacteristicsDto models on service layer.
 * It is realisation of CarCharacteristicsDtoService interface
 *
 * @author Katuranau Maksimilyan
 * @see CarCharacteristicsDtoService
 */
@Service
public class CarCharacteristicsDtoServiceImpl implements CarCharacteristicsDtoService {

    private CarCharacteristicsDtoDao carCharacteristicsDtoDao;

    private static final Logger LOGGER = LogManager.getLogger(CarCharacteristicsDtoServiceImpl.class);

    /**
     * Instantiates a new Car characteristics dto service.
     *
     * @param carCharacteristicsDtoDao the car characteristics dto dao
     */
    @Autowired
    public CarCharacteristicsDtoServiceImpl(final CarCharacteristicsDtoDao carCharacteristicsDtoDao) {
        this.carCharacteristicsDtoDao = carCharacteristicsDtoDao;
    }


    /**
     * Gets car characteristics.
     * It is used to reduce the count of sql queries
     *
     * @return the car characteristics
     */
    @Override
    public CarCharacteristicsDto getCarCharacteristics() {
        LOGGER.debug("method getCarCharacteristics");
        return carCharacteristicsDtoDao.getCarCharacteristics();
    }
}
