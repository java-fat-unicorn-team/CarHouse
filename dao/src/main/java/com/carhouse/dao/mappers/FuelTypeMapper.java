package com.carhouse.dao.mappers;

import com.carhouse.model.FuelType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The Class is used to create FuelType from data obtained from database.
 * @see FuelType
 * @author Katuranau Maksimilyan
 */
@Component
public class FuelTypeMapper implements RowMapper<FuelType> {

    private static final String FUEL_TYPE_ID = "fuel_type_id";
    private static final String FUEL_TYPE = "fuel_type";
    private static final Logger LOGGER = LogManager.getLogger(FuelTypeMapper.class);

    @Override
    public FuelType mapRow(final ResultSet resultSet, final int i) throws SQLException {
        FuelType fuelType = new FuelType(resultSet.getInt(FUEL_TYPE_ID), resultSet.getString(FUEL_TYPE));
        LOGGER.debug("row ({}, {}) has been mapped", fuelType.getFuelTypeId(), fuelType.getFuelType());
        return fuelType;
    }
}
