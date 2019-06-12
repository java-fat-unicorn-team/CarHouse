package com.spring.rest.dao.impl;

import com.spring.rest.dao.TransmissionDao;
import com.spring.rest.model.Transmission;
import com.spring.rest.model.mappers.TransmissionMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The class provides methods to manage Transmission model.
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
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    /**
     * mapper to get Transmission object.
     */
    private final TransmissionMapper transmissionMapper;
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger(TransmissionDaoImpl.class);

    /**
     * Instantiates a new Transmission dao.
     *
     * @param namedParameterJdbcTemplate the named parameter jdbc template
     * @param transmissionMapper         the transmission mapper
     */
    public TransmissionDaoImpl(final NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                               final TransmissionMapper transmissionMapper) {
        LOGGER.debug("TransmissionDaoImpl was created");
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.transmissionMapper = transmissionMapper;
    }

    /**
     * Gets transmissions.
     *
     * @return the list of transmissions
     */
    @Override
    public List<Transmission> getTransmissions() {
        LOGGER.info("method getTransmissions was called");
        return namedParameterJdbcTemplate.query(GET_TRANSMISSIONS_SQL, transmissionMapper);
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
        LOGGER.info("method getTransmission with parameter: {} was called", index);
        return namedParameterJdbcTemplate.queryForObject(GET_TRANSMISSION_SQL, parameters, transmissionMapper);
    }
}
