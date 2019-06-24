package com.spring.dao.impl;

import com.spring.model.CarFeature;
import com.spring.model.mappers.CarFeatureMapper;
import com.spring.dao.CarFeatureDao;
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
     * Gets car features.
     *
     * @param carCharacteristicsId the car id
     * @return the list of car features
     */
    @Override
    public List<CarFeature> getCarFeatures(final int carCharacteristicsId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("carCharacteristicsId", carCharacteristicsId);
        LOGGER.debug("method getCarFeatures with parameter: {} was called", carCharacteristicsId);
        return namedParameterJdbcTemplate.query(GET_LIST_CAR_FEATURES_SQL, parameters, carFeatureMapper);
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
        LOGGER.debug("method getCarFeature with parameter: {} was called", index);
        return namedParameterJdbcTemplate.queryForObject(GET_CAR_FEATURE_SQL, parameters, carFeatureMapper);
    }

    /**
     * Add car feature.
     *
     * @param carFeature the car feature
     * @param carCharacteristicsId      the car id
     * @return car feature id
     */
    @Override
    public Integer addCarFeature(final String carFeature, final int carCharacteristicsId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("carFeature", carFeature)
                .addValue("carCharacteristicsId", carCharacteristicsId);
        LOGGER.debug("method addCarFeature with parameters: {}, {}", carFeature, carCharacteristicsId);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(ADD_CAR_FEATURE_SQL, parameters, keyHolder);
        return keyHolder.getKey().intValue();
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
        LOGGER.debug("method updateCarFeature with parameters: {}, {}", carFeature, carFeatureId);
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
        LOGGER.debug("method deleteCarFeature with parameter: {} was called", index);
        namedParameterJdbcTemplate.update(DELETE_CAR_FEATURE_SQL, parameters);
    }
}