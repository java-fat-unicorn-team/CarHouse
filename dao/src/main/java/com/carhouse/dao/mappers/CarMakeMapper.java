package com.carhouse.dao.mappers;

import com.carhouse.model.CarMake;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The Class is used to create CarMake from data obtained from database.
 * @see CarMake
 * @author Katuranau Maksimilyan
 */
@Component
public class CarMakeMapper implements RowMapper<CarMake> {

    private static final String CAR_MAKE_ID = "car_make_id";
    private static final String CAR_MAKE = "car_make";
    private static final Logger LOGGER = LogManager.getLogger(CarMakeMapper.class);

    @Override
    public CarMake mapRow(final ResultSet resultSet, final int i) throws SQLException {
        CarMake carMake = new CarMake(resultSet.getInt(CAR_MAKE_ID), resultSet.getString(CAR_MAKE));
        LOGGER.debug("row ({}, {}) has been mapped", carMake.getCarMakeId(), carMake.getCarMake());
        return carMake;
    }
}
