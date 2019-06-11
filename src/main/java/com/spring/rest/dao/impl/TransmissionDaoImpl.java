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

/**
 * The class provides methods to get Transmission model.
 * The class stores date in database
 */
@Repository
public class TransmissionDaoImpl implements TransmissionDao {
    /**
     * SQL query to get transmission.
     */
    @Value("${transmission.get}")
    private String GET_TRANSMISSION_SQL;
    /**
     * SQL query to get transmissions.
     */
    @Value("${transmissions.get}")
    private String GET_TRANSMISSIONS_SQL;

    /**
     * named parameter JDBC template.
     */
    private final NamedParameterJdbcTemplate jdbcTemplate;
    /**
     * mapper to get Transmission object.
     */
    private final TransmissionMapper transmissionMapper;

    /**
     * Instantiates a new Transmission dao.
     *
     * @param jdbcTemplate       the jdbc template
     * @param transmissionMapper the transmission mapper
     */
    public TransmissionDaoImpl(final NamedParameterJdbcTemplate jdbcTemplate,
                               final TransmissionMapper transmissionMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.transmissionMapper = transmissionMapper;
    }

    /**
     * Gets transmissions.
     *
     * @return the transmissions
     */
    @Override
    public List<Transmission> getTransmissions() {
        return jdbcTemplate.query(GET_TRANSMISSIONS_SQL,
                transmissionMapper);
    }

    /**
     * Gets transmission.
     *
     * @param index the index
     * @return the transmission
     */
    @Override
    public Transmission getTransmission(final int index) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", index);
        return jdbcTemplate.queryForObject(GET_TRANSMISSION_SQL,
                parameters, transmissionMapper);
    }
}
