package com.spring.rest.model.mappers;

import com.spring.rest.model.Transmission;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The is used to create Transmission from data returned from database.
 */
@Component
public class TransmissionMapper implements RowMapper<Transmission> {

    /**
     * The constant TRANSMISSION_ID.
     */
    public static final String TRANSMISSION_ID = "transmission_id";
    /**
     * The constant TRANSMISSION.
     */
    public static final String TRANSMISSION = "transmission";

    @Override
    public Transmission mapRow(final ResultSet resultSet, final int i)
            throws SQLException {
        return new Transmission(
                resultSet.getInt(TRANSMISSION_ID),
                resultSet.getString(TRANSMISSION));
    }
}
