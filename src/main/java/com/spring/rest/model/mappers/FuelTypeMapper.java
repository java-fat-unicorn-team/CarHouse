package com.spring.rest.model.mappers;

import com.spring.rest.model.FuelType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The is used to create FuelType from data returned from database.
 */
@Component
public class FuelTypeMapper implements RowMapper<FuelType> {
    /**
     * The constant FUEL_TYPE_ID.
     */
    public static final String FUEL_TYPE_ID = "fuel_type_id";
    /**
     * The constant FUEL_TYPE.
     */
    public static final String FUEL_TYPE = "fuel_type";

    @Override
    public FuelType mapRow(final ResultSet resultSet, final int i)
            throws SQLException {
        return new FuelType(resultSet.getInt(FUEL_TYPE_ID),
                resultSet.getString(FUEL_TYPE));
    }
}
