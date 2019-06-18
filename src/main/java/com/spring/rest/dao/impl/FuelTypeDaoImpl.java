package com.spring.rest.dao.impl;

import com.spring.rest.dao.FuelTypeDao;
import com.spring.rest.model.FuelType;
import com.spring.rest.model.mappers.FuelTypeMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The class provides methods to manage FuelType model.
 * The class stores date in database
 * @author Katuranau Maksimilyan
 */
@Repository
public class FuelTypeDaoImpl implements FuelTypeDao {
    /**
     * SQL query to get fuel type.
     */
    @Value("${fuel.type.get}")
    private String GET_FUEL_TYPE_SQL;
    /**
     * SQL query to get list of fuel types.
     */
    @Value("${fuel.types.list.get}")
    private String GET_LIST_FUEL_TYPES_SQL;
    /**
     * SQL query to add fuel type.
     */
    @Value("${fuel.type.add}")
    private String ADD_FUEL_TYPE;
    /**
     * SQL query to update fuel type.
     */
    @Value("${fuel.type.update}")
    private String UPDATE_FUEL_TYPE;
    /**
     * SQL query to delete fuel type.
     */
    @Value("${fuel.type.delete}")
    private String DELETE_FUEL_TYPE;

    /**
     * named parameter JDBC template.
     */
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    /**
     * mapper to get FuelType object.
     */
    private final FuelTypeMapper fuelTypeMapper;
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger(FuelTypeDaoImpl.class);

    /**
     * Instantiates a new Fuel type dao.
     *
     * @param namedParameterJdbcTemplate the named parameter jdbc template
     * @param fuelTypeMapper             the fuel type mapper
     */
    @Autowired
    public FuelTypeDaoImpl(final NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                           final FuelTypeMapper fuelTypeMapper) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.fuelTypeMapper = fuelTypeMapper;
    }

    /**
     * Gets fuel types.
     *
     * @return the list of fuel types
     */
    @Override
    public List<FuelType> getFuelTypes() {
        LOGGER.debug("method getFuelTypes was called");
        return namedParameterJdbcTemplate.query(GET_LIST_FUEL_TYPES_SQL, fuelTypeMapper);
    }

    /**
     * Gets fuel type.
     *
     * @param index the index
     * @return the fuel type
     */
    @Override
    public FuelType getFuelType(final int index) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", index);
        LOGGER.debug("method getFuelType with parameter: {} was called", index);
        return namedParameterJdbcTemplate.queryForObject(GET_FUEL_TYPE_SQL, parameters, fuelTypeMapper);
    }

    /**
     * Add fuel type.
     *
     * @param fuelType the fuel type
     * @return fuel type id
     */
    @Override
    public Integer addFuelType(final String fuelType) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("fuelType", fuelType);
        LOGGER.debug("method addFuelType with parameter: {} was called", fuelType);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(ADD_FUEL_TYPE, parameters, keyHolder);
        return keyHolder.getKey().intValue();
    }

    /**
     * Delete fuel type.
     *
     * @param index the index
     */
    @Override
    public void deleteFuelType(final int index) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", index);
        LOGGER.debug("method deleteFuelType with parameter: {} was called", index);
        namedParameterJdbcTemplate.update(DELETE_FUEL_TYPE, parameters);
    }

    /**
     * Update fuel type.
     *
     * @param index    the index
     * @param fuelType the fuel type
     */
    @Override
    public void updateFuelType(final int index, final String fuelType) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("fuelType", fuelType)
                .addValue("id", index);
        LOGGER.debug("method updateFuelType with parameters: {}, {} was called", index, fuelType);
        namedParameterJdbcTemplate.update(UPDATE_FUEL_TYPE, parameters);

    }
}
