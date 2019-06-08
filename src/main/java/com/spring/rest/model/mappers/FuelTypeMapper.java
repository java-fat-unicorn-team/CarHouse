package com.spring.rest.model.mappers;

import com.spring.rest.model.FuelType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class FuelTypeMapper implements RowMapper<FuelType> {
    public static final String FUEL_TYPE_ID = "fuel_type_id";
    public static final String FUEL_TYPE = "fuel_type";

    @Override
    public FuelType mapRow(ResultSet resultSet, int i) throws SQLException {
        return new FuelType(resultSet.getInt(FUEL_TYPE_ID),
                resultSet.getString(FUEL_TYPE));
    }
}
