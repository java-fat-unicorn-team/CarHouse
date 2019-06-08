package com.spring.rest.dao.impl;

import com.spring.rest.dao.FuelTypeDao;
import com.spring.rest.model.FuelType;
import com.spring.rest.model.mappers.FuelTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The type Fuel type dao.
 */
@Repository
public class FuelTypeDaoImpl implements FuelTypeDao {
    @Value("${get.fuel.type}")
    private String GET_FUEL_TYPE_SQL;
    @Value("${get.all.fuel.types}")
    private String GET_ALL_FUEL_TYPES_SQL;
    @Value("${add.fuel.type}")
    private String ADD_FUEL_TYPE;
    @Value("${update.fuel.type}")
    private String UPDATE_FUEL_TYPE;
    @Value("${delete.fuel.type}")
    private String DELETE_FUEL_TYPE;


    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final FuelTypeMapper fuelTypeMapper;

    /**
     * Instantiates a new Fuel type dao.
     *
     * @param jdbcTemplate   the jdbc template
     * @param fuelTypeMapper the fuel type mapper
     */
    @Autowired
    public FuelTypeDaoImpl(final NamedParameterJdbcTemplate jdbcTemplate,
                           final FuelTypeMapper fuelTypeMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.fuelTypeMapper = fuelTypeMapper;
    }

    @Override
    public List<FuelType> getAllFuelTypes() {
        return jdbcTemplate.query(
                GET_ALL_FUEL_TYPES_SQL,
                fuelTypeMapper);
    }

    @Override
    public FuelType getFuelType(final int index) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", index);
        return jdbcTemplate.queryForObject(
                GET_FUEL_TYPE_SQL, parameters,
                fuelTypeMapper);
    }

    @Override
    public void addFuelType(final String fuelType) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("fuelType", fuelType);
        jdbcTemplate.update(ADD_FUEL_TYPE, parameters);
    }

    @Override
    public void deleteFuelType(final int index) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", index);

        jdbcTemplate.update(DELETE_FUEL_TYPE, parameters);
    }

    @Override
    public void updateFuelType(final int index, final String fuelType) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("fuelType", fuelType)
                .addValue("id", index);
        jdbcTemplate.update(UPDATE_FUEL_TYPE, parameters);

    }
}
