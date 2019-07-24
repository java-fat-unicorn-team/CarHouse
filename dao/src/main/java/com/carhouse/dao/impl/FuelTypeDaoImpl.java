package com.carhouse.dao.impl;

import com.carhouse.dao.FuelTypeDao;
import com.carhouse.dao.mappers.FuelTypeMapper;
import com.carhouse.model.FuelType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The class provides method to get FuelType models.
 * The class works with date from database
 * It is realisation of FuelTypeDao interface
 *
 * @author Katuranau Maksimilyan
 * @see FuelTypeDao
 */
@Repository
public class FuelTypeDaoImpl implements FuelTypeDao {
    /**
     * SQL query to get list of fuel types.
     */
    @Value("${fuel.types.list.get}")
    private String GET_LIST_FUEL_TYPES_SQL;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final FuelTypeMapper fuelTypeMapper;

    private static final Logger LOGGER = LogManager.getLogger(FuelTypeDaoImpl.class);

    /**
     * Instantiates a new Fuel type dao.
     *
     * @param namedParameterJdbcTemplate for connection with database
     * @param fuelTypeMapper             mapper to get FuelType object
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
}
