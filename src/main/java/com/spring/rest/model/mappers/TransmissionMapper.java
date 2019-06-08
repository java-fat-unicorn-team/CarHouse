package com.spring.rest.model.mappers;

import com.spring.rest.model.Transmission;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TransmissionMapper implements RowMapper<Transmission> {

    public static final String TRANSMISSION_ID = "transmission_id";
    public static final String TRANSMISSION = "transmission";

    @Override
    public Transmission mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Transmission(
                resultSet.getInt(TRANSMISSION_ID), resultSet.getString(TRANSMISSION));
    }
}
