package com.carhouse.dao.impl;

import com.carhouse.dao.CarFeatureDao;
import com.carhouse.model.CarFeature;
import com.carhouse.dao.mappers.CarFeatureMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The class provides methods to manage CarFeature model.
 * The class stores date in database
 * It is realisation of CarFeatureDao interface
 * @see CarFeatureDao
 * @author Katuranau Maksimilyan
 */
@Repository
public class CarFeatureDaoImpl implements CarFeatureDao {
    /**
     * SQL query to get car feature.
     */
    @Value("${car.feature.get}")
    private String GET_CAR_FEATURE_SQL;
    /**
     * SQL query to get list of car features.
     */
    @Value("${car.features.list.get}")
    private String GET_LIST_CAR_FEATURES_SQL;
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
     * Gets all features of car with id which is provided.
     *
     * @param carId the car id
     * @return the list of car features
     */
    @Override
    public List<CarFeature> getCarFeatures(final int carId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("carId", carId);
        LOGGER.debug("method getCarFeatures with parameter: [{}]", carId);
        return namedParameterJdbcTemplate.query(GET_LIST_CAR_FEATURES_SQL, parameters, carFeatureMapper);
    }

    /**
     * Gets car feature by.
     *
     * @param id the car feature id
     * @return the car feature
     */
    @Override
    public CarFeature getCarFeature(final int id) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        LOGGER.debug("method getCarFeature with parameter: [{}]", id);
        return namedParameterJdbcTemplate.queryForObject(GET_CAR_FEATURE_SQL, parameters, carFeatureMapper);
    }

    /**
     * Add car feature to car with provided id.
     *
     * @param carFeature                the car feature name
     * @param carId                     the car id
     * @return                          car feature id
     */
    @Override
    public Integer addCarFeature(final String carFeature, final int carId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("carFeature", carFeature)
                .addValue("carId", carId);
        LOGGER.debug("method addCarFeature with parameters: [{}, {}]", carFeature, carId);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(ADD_CAR_FEATURE_SQL, parameters, keyHolder);
        return keyHolder.getKey().intValue();
    }

    /**
     * Update car feature by id.
     *
     * @param carFeature   new car feature name
     * @param carFeatureId the car feature id
     */
    @Override
    public void updateCarFeature(final String carFeature, final int carFeatureId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("carFeature", carFeature)
                .addValue("id", carFeatureId);
        LOGGER.debug("method updateCarFeature with parameters: [{}, {}]", carFeature, carFeatureId);
        namedParameterJdbcTemplate.update(UPDATE_CAR_FEATURE_SQL, parameters);
    }

    /**
     * Delete car feature by id.
     *
     * @param id the index
     */
    @Override
    public void deleteCarFeature(final int id) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        LOGGER.debug("method deleteCarFeature with parameter: [{}]", id);
        namedParameterJdbcTemplate.update(DELETE_CAR_FEATURE_SQL, parameters);
    }
}
