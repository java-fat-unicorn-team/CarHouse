package com.carhouse.dao.mappers;

import com.carhouse.model.CarModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The Class is used to create CarModel from data obtained from database.
 * @see CarModel
 * @author Katuranau Maksimilyan
 */
@Component
public class CarModelMapper implements RowMapper<CarModel> {

    private static final String CAR_MODEL_ID = "car_model_id";
    private static final String CAR_MODEL = "car_model";
    private CarMakeMapper carMakeMapper;
    private static final Logger LOGGER = LogManager.getLogger(CarModelMapper.class);

    /**
     * Instantiates a new Car model mapper.
     *
     * @param pCarMakeMapper the p car make mapper
     */
    @Autowired
    public CarModelMapper(final CarMakeMapper pCarMakeMapper) {
        carMakeMapper = pCarMakeMapper;
    }

    @Override
    public CarModel mapRow(final ResultSet resultSet, final int i) throws SQLException {
        CarModel carModel = new CarModel(resultSet.getInt(CAR_MODEL_ID), carMakeMapper.mapRow(resultSet, i),
                resultSet.getString(CAR_MODEL));
        LOGGER.debug("row ({}, {}) has been mapped", carModel.getCarModelId(), carModel.getCarModel());
        return carModel;
    }
}
