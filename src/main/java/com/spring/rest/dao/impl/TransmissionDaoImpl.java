package com.spring.rest.dao.impl;

import com.spring.rest.dao.TransmissionDao;
import com.spring.rest.model.Transmission;
import com.spring.rest.model.mappers.TransmissionMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransmissionDaoImpl implements TransmissionDao {
    @Value("${get.transmission}")
    private String GET_TRANSMISSION_SQL;
    @Value("${get.all.transmissions}")
    private String GET_ALL_TRANSMISSIONS_SQL;

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final TransmissionMapper transmissionMapper;

    public TransmissionDaoImpl(NamedParameterJdbcTemplate jdbcTemplate,
                               TransmissionMapper transmissionMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.transmissionMapper = transmissionMapper;
    }

    @Override
    public List<Transmission> getAllTransmission() {
        return jdbcTemplate.query(GET_ALL_TRANSMISSIONS_SQL,
                transmissionMapper);
    }

    @Override
    public Transmission getTransmission(int index) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", index);
        return jdbcTemplate.queryForObject(GET_TRANSMISSION_SQL,
                parameters, transmissionMapper);
    }
}
