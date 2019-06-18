package com.spring.rest.model.mappers;

import com.spring.rest.model.Transmission;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The Class is used to create Transmission from data obtained from database.
 * @author Katuranau Maksimilyan
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
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger(TransmissionMapper.class);

    @Override
    public Transmission mapRow(final ResultSet resultSet, final int i) throws SQLException {
        Transmission transmission = new Transmission(resultSet.getInt(TRANSMISSION_ID),
                resultSet.getString(TRANSMISSION));
        LOGGER.debug("row ({}, {}) has been mapped", resultSet.getInt(TRANSMISSION_ID),
                resultSet.getString(TRANSMISSION));
        return transmission;
    }
}
