package com.carhouse.dao.impl;

import com.carhouse.dao.CarModelDao;
import com.carhouse.dao.mappers.CarModelMapper;
import com.carhouse.model.CarModel;
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
 * It is realisation of CarModelDao interface
 *
 * @author Katuranau Maksimilyan
 * @see CarModelDao
 */
@Repository
public class CarModelDaoImpl implements CarModelDao {

    @Value("${car.model.get}")
    private String GET_CAR_MODEL_SQL;

    @Value("${car.models.list.get}")
    private String GET_LIST_CAR_MODELS_SQL;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final CarModelMapper carModelMapper;

    private static final Logger LOGGER = LogManager.getLogger(CarModelDaoImpl.class);

    /**
     * Instantiates a new Car model dao.
     *
     * @param namedParameterJdbcTemplate for connection with database
     * @param carModelMapper             mapper to get CarModel object
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
        LOGGER.debug("method getCarModels");
        return namedParameterJdbcTemplate.query(GET_LIST_CAR_MODELS_SQL, carModelMapper);
    }

    /**
     * Gets car model by id.
     *
     * @param id the car model id.
     * @return the car model
     */
    @Override
    public CarModel getCarModel(final int id) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        LOGGER.debug("method getCarModel with parameter: [{}]", id);
        return namedParameterJdbcTemplate.queryForObject(GET_CAR_MODEL_SQL, parameters, carModelMapper);
    }
}
