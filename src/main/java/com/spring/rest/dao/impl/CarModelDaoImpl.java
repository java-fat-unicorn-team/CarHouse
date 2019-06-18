package com.spring.rest.dao.impl;

import com.spring.rest.dao.CarModelDao;
import com.spring.rest.model.CarModel;
import com.spring.rest.model.mappers.CarModelMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The class provides methods to manage CarModel model.
 * The class stores date in database
 * @author Katuranau Maksimilyan
 */
@Repository
public class CarModelDaoImpl implements CarModelDao {
    /**
     * SQL query to get car model.
     */
    @Value("${car.model.get}")
    private String GET_CAR_MODEL_SQL;
    /**
     * SQL query to get list of car models.
     */
    @Value("${car.models.list.get}")
    private String GET_LIST_CAR_MODELS_SQL;

    /**
     * named parameter JDBC template.
     */
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    /**
     * mapper to get CarModel object.
     */
    private final CarModelMapper carModelMapper;
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger(CarModelDaoImpl.class);

    /**
     * Instantiates a new Car model dao.
     *
     * @param namedParameterJdbcTemplate the named parameter jdbc template
     * @param carModelMapper             the car model mapper
     */
    public CarModelDaoImpl(final NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                           final CarModelMapper carModelMapper) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.carModelMapper = carModelMapper;
    }

    /**
     * Gets car models.
     *
     * @return the list of car models
     */
    @Override
    public List<CarModel> getCarModels() {
        LOGGER.debug("method getCarModels was called");
        return namedParameterJdbcTemplate.query(GET_LIST_CAR_MODELS_SQL, carModelMapper);
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
        LOGGER.debug("method getCarModel with parameter: {} was called", index);
        return namedParameterJdbcTemplate.queryForObject(GET_CAR_MODEL_SQL, parameters, carModelMapper);
    }
}
