package com.spring.rest.dao.impl;

import com.spring.rest.dao.CarModelDao;
import com.spring.rest.model.CarModel;
import com.spring.rest.model.mappers.CarModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The class provides methods to get CarModel model.
 * The class stores date in database
 */
@Repository
public class CarModelDaoImpl implements CarModelDao {
    /**
     * SQL query to get car model.
     */
    @Value("${car.model.get}")
    private String GET_CAR_MODEL_SQL;
    /**
     * SQL query to get car models.
     */
    @Value("${car.models.get}")
    private String GET_CAR_MODELS_SQL;

    /**
     * named parameter JDBC template.
     */
    private final NamedParameterJdbcTemplate jdbcTemplate;
    /**
     * mapper to get CarModel object.
     */
    private final CarModelMapper carModelMapper;

    /**
     * Instantiates a new Car model dao.
     *
     * @param jdbcTemplate   the jdbc template
     * @param carModelMapper the car model mapper
     */
    public CarModelDaoImpl(final NamedParameterJdbcTemplate jdbcTemplate,
                           final CarModelMapper carModelMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.carModelMapper = carModelMapper;
    }

    /**
     * Gets car models.
     *
     * @return the car models
     */
    @Override
    public List<CarModel> getCarModels() {
        return jdbcTemplate.query(GET_CAR_MODELS_SQL, carModelMapper);
    }

    /**
     * Gets car model.
     *
     * @param index the index
     * @return the car model
     */
    @Override
    public CarModel getCarModel(final int index) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", index);
        return jdbcTemplate.queryForObject(GET_CAR_MODEL_SQL, parameters,
                carModelMapper);
    }
}
