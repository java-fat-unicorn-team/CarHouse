package com.spring.rest.model.mappers;

import com.spring.rest.model.CarFeature;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CarFeatureMapper implements RowMapper<CarFeature> {
    public static final String CAR_FEATURE_ID = "car_feature_id";
    public static final String CAR_FEATURE = "car_feature";
    public static final String CAR_ID = "car_id";

    @Override
    public CarFeature mapRow(ResultSet resultSet, int i) throws SQLException {
        return new CarFeature(resultSet.getInt(CAR_FEATURE_ID),
                resultSet.getString(CAR_FEATURE), resultSet.getInt(CAR_ID));
    }
}
