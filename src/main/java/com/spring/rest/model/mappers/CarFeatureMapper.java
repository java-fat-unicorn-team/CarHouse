package com.spring.rest.model.mappers;

import com.spring.rest.model.CarFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The is used to create CarFeature from data returned from database.
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
     * Instantiates a new Car feature mapper.
     *
     * @param carCharacteristicsMapper the car characteristics mapper
     */
    @Autowired
    public CarFeatureMapper(
            final CarCharacteristicsMapper carCharacteristicsMapper) {
        this.carCharacteristicsMapper = carCharacteristicsMapper;
    }

    @Override
    public CarFeature mapRow(final ResultSet resultSet, final int i)
            throws SQLException {
        return new CarFeature(resultSet.getInt(CAR_FEATURE_ID),
                resultSet.getString(CAR_FEATURE),
                carCharacteristicsMapper.mapRow(resultSet, i));
    }
}
