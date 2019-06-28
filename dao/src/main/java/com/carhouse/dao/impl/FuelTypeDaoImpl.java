package com.carhouse.dao.impl;

import com.carhouse.dao.FuelTypeDao;
import com.carhouse.model.FuelType;
import com.carhouse.dao.mappers.FuelTypeMapper;
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
 * It is realisation of FuelTypeDao interface
 * @see FuelTypeDao
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
        LOGGER.debug("method getFuelTypes");
        return namedParameterJdbcTemplate.query(GET_LIST_FUEL_TYPES_SQL, fuelTypeMapper);
    }

    /**
     * Gets fuel type by id.
     *
     * @param id the fuel type id.
     * @return the fuel type
     */
    @Override
    public FuelType getFuelType(final int id) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        LOGGER.debug("method getFuelType with parameter: [{}]", id);
        return namedParameterJdbcTemplate.queryForObject(GET_FUEL_TYPE_SQL, parameters, fuelTypeMapper);
    }

    /**
     * Add fuel type.
     * Database generate new id.
     *
     * @param fuelType the fuel type
     * @return fuel type id
     */
    @Override
    public Integer addFuelType(final String fuelType) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("fuelType", fuelType);
        LOGGER.debug("method addFuelType with parameter: [{}]", fuelType);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(ADD_FUEL_TYPE, parameters, keyHolder);
        return keyHolder.getKey().intValue();
    }

    /**
     * Update fuel type by id.
     *
     * @param id        the fuel type id.
     * @param fuelType  the fuel type
     */
    @Override
    public void updateFuelType(final int id, final String fuelType) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("fuelType", fuelType)
                .addValue("id", id);
        LOGGER.debug("method updateFuelType with parameters: [{}, {}]", id, fuelType);
        namedParameterJdbcTemplate.update(UPDATE_FUEL_TYPE, parameters);

    }

    /**
     * Delete fuel type by id.
     *
     * @param id the fuel type id.
     */
    @Override
    public void deleteFuelType(final int id) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        LOGGER.debug("method deleteFuelType with parameter: [{}]", id);
        namedParameterJdbcTemplate.update(DELETE_FUEL_TYPE, parameters);
    }
}
