package com.spring.rest.model.mappers;

import com.spring.rest.model.CarMake;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Car make mapper.
 */
@Component
public class CarMakeMapper implements RowMapper<CarMake> {
    /**
     * The constant CAR_MAKE_ID.
     */
    public static final String CAR_MAKE_ID = "car_make_id";
    /**
     * The constant CAR_MAKE.
     */
    public static final String CAR_MAKE = "car_make";

    @Override
    public CarMake mapRow(final ResultSet resultSet, final int i)
            throws SQLException {
        return new CarMake(resultSet.getInt(CAR_MAKE_ID),
                resultSet.getString(CAR_MAKE));
    }
}
