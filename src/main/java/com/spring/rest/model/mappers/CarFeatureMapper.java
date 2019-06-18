package com.spring.rest.model.mappers;

import com.spring.rest.model.CarFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The Class is used to create CarFeature from data obtained from database.
 * @author Katuranau Maksimilyan
 */
@Component
public class CarFeatureMapper implements RowMapper<CarFeature> {
    /**
     * The constant CAR_FEATURE_ID.
     */
    public static final String CAR_FEATURE_ID = "car_feature_id";
    /**
     * The constant CAR_FEATURE.
     */
    public static final String CAR_FEATURE = "car_feature";
    /**
     * mapper to get CarCharacteristics object.
     */
    private CarCharacteristicsMapper carCharacteristicsMapper;
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger(CarFeatureMapper.class);

    /**
     * Instantiates a new Car feature mapper.
     *
     * @param carCharacteristicsMapper the car characteristics mapper
     */
    @Autowired
    public CarFeatureMapper(final CarCharacteristicsMapper carCharacteristicsMapper) {
        this.carCharacteristicsMapper = carCharacteristicsMapper;
    }

    @Override
    public CarFeature mapRow(final ResultSet resultSet, final int i) throws SQLException {
        CarFeature carFeature = new CarFeature(resultSet.getInt(CAR_FEATURE_ID), resultSet.getString(CAR_FEATURE),
                carCharacteristicsMapper.mapRow(resultSet, i));
        LOGGER.debug("row ({}, {}) has been mapped", resultSet.getInt(CAR_FEATURE_ID),
                resultSet.getString(CAR_FEATURE));
        return carFeature;
    }
}
