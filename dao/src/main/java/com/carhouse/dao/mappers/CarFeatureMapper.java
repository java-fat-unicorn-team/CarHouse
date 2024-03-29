package com.carhouse.dao.mappers;

import com.carhouse.model.CarFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The Class is used to create CarFeature from data obtained from database.
 *
 * @author Katuranau Maksimilyan
 * @see CarFeature
 */
@Component
public class CarFeatureMapper implements RowMapper<CarFeature> {

    private static final String CAR_FEATURE_ID = "car_feature_id";
    private static final String CAR_FEATURE = "car_feature";
    private static final Logger LOGGER = LogManager.getLogger(CarFeatureMapper.class);

    @Override
    public CarFeature mapRow(final ResultSet resultSet, final int i) throws SQLException {
        CarFeature carFeature = new CarFeature(resultSet.getInt(CAR_FEATURE_ID), resultSet.getString(CAR_FEATURE));
        LOGGER.debug("row ({}, {}) has been mapped", carFeature.getCarFeatureId(), carFeature.getCarFeature());
        return carFeature;
    }
}
