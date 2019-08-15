package com.carhouse.dao.impl;

import com.carhouse.dao.TransmissionDao;
import com.carhouse.dao.mappers.TransmissionMapper;
import com.carhouse.model.Transmission;
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
 * It is realisation of TransmissionDao interface
 *
 * @author Katuranau Maksimilyan
 * @see TransmissionDao
 */
@Repository
public class TransmissionDaoImpl implements TransmissionDao {

    @Value("${transmission.get}")
    private String GET_TRANSMISSION_SQL;

    @Value("${transmissions.list.get}")
    private String GET_LIST_TRANSMISSIONS_SQL;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final TransmissionMapper transmissionMapper;

    private static final Logger LOGGER = LogManager.getLogger(TransmissionDaoImpl.class);

    /**
     * Instantiates a new Transmission dao.
     *
     * @param namedParameterJdbcTemplate for connection with database
     * @param transmissionMapper         mapper to get Transmission object
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
        LOGGER.debug("method getTransmissions");
        return namedParameterJdbcTemplate.query(GET_LIST_TRANSMISSIONS_SQL, transmissionMapper);
    }

    /**
     * Gets transmission by id.
     *
     * @param id the transmission id.
     * @return the transmission
     */
    @Override
    public Transmission getTransmission(final int id) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        LOGGER.debug("method getTransmission with parameter: [{}]", id);
        return namedParameterJdbcTemplate.queryForObject(GET_TRANSMISSION_SQL, parameters, transmissionMapper);
    }
}
