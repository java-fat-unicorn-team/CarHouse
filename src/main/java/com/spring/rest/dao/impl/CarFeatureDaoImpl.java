package com.spring.rest.dao.impl;

import com.spring.rest.dao.CarFeatureDao;
import com.spring.rest.model.CarFeature;
import com.spring.rest.model.mappers.CarFeatureMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The class provides methods to manage CarFeature model.
 * The class stores date in database
 */
@Repository
public class CarFeatureDaoImpl implements CarFeatureDao {
    /**
     * SQL query to get car feature.
     */
    @Value("${car.feature.get}")
    private String GET_CAR_FEATURE_SQL;
    /**
     * SQL query to get car features.
     */
    @Value("${car.features.get}")
    private String GET_CAR_FEATURES_SQL;
    /**
     * SQL query to add car feature.
     */
    @Value("${car.feature.add}")
    private String ADD_CAR_FEATURE_SQL;
    /**
     * SQL query to update car feature.
     */
    @Value("${car.feature.update}")
    private String UPDATE_CAR_FEATURE_SQL;
    /**
     * SQL query to delete car feature.
     */
    @Value("${car.feature.delete}")
    private String DELETE_CAR_FEATURE_SQL;

    /**
     * named parameter JDBC template.
     */
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    /**
     * mapper to get CarFeature object.
     */
    private final CarFeatureMapper carFeatureMapper;
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger(CarFeatureDaoImpl.class);

    /**
     * Instantiates a new Car feature dao.
     *
     * @param namedParameterJdbcTemplate the named parameter jdbc template
     * @param carFeatureMapper           the car feature mapper
     */
    public CarFeatureDaoImpl(final NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                             final CarFeatureMapper carFeatureMapper) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.carFeatureMapper = carFeatureMapper;
    }


    /**
     * Gets car features.
     *
     * @param carId the car id
     * @return the list of car features
     */
    @Override
    public List<CarFeature> getCarFeatures(final int carId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("carId", carId);
        LOGGER.info("method getCarFeatures with parameter: {} was called", carId);
        return namedParameterJdbcTemplate.query(GET_CAR_FEATURES_SQL, parameters, carFeatureMapper);
    }

    /**
     * Gets car feature.
     *
     * @param index the index
     * @return the car feature
     */
    @Override
    public CarFeature getCarFeature(final int index) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", index);
        LOGGER.info("method getCarFeature with parameter: {} was called", index);
        return namedParameterJdbcTemplate.queryForObject(GET_CAR_FEATURE_SQL, parameters, carFeatureMapper);
    }

    /**
     * Add car feature.
     *
     * @param carFeature the car feature
     * @param carId      the car id
     */
    @Override
    public void addCarFeature(final String carFeature, final int carId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("carFeature", carFeature)
                .addValue("carId", carId);
        LOGGER.info("method addCarFeature with parameters: {}, {}", carFeature, carId);
        namedParameterJdbcTemplate.update(ADD_CAR_FEATURE_SQL, parameters);
    }

    /**
     * Update car feature.
     *
     * @param carFeature   the car feature
     * @param carFeatureId the car feature id
     */
    @Override
    public void updateCarFeature(final String carFeature,
                                 final int carFeatureId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("carFeature", carFeature)
                .addValue("id", carFeatureId);
        LOGGER.info("method updateCarFeature with parameters: {}, {}", carFeature, carFeatureId);
        namedParameterJdbcTemplate.update(UPDATE_CAR_FEATURE_SQL, parameters);
    }

    /**
     * Delete car feature.
     *
     * @param index the index
     */
    @Override
    public void deleteCarFeature(final int index) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", index);
        LOGGER.info("method deleteCarFeature with parameter: {} was called", index);
        namedParameterJdbcTemplate.update(DELETE_CAR_FEATURE_SQL, parameters);
    }
}
