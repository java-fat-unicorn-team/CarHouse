package com.spring.rest.model.mappers;

import com.spring.rest.model.CarFeature;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Car feature mapper.
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
     * The constant CAR_ID.
     */
    public static final String CAR_ID = "car_id";

    @Override
    public CarFeature mapRow(final ResultSet resultSet, final int i)
            throws SQLException {
        return new CarFeature(resultSet.getInt(CAR_FEATURE_ID),
                resultSet.getString(CAR_FEATURE), resultSet.getInt(CAR_ID));
    }
}
