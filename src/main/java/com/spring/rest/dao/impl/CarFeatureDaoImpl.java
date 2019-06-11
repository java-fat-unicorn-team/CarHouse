package com.spring.rest.dao.impl;

import com.spring.rest.dao.CarFeatureDao;
import com.spring.rest.model.CarFeature;
import com.spring.rest.model.mappers.CarFeatureMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The class provides CRUD operations with CarFeature model.
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
    private final NamedParameterJdbcTemplate jdbcTemplate;
    /**
     * mapper to get CarFeature object.
     */
    private final CarFeatureMapper carFeatureMapper;

    /**
     * Instantiates a new Car feature dao.
     *
     * @param jdbcTemplate     the jdbc template
     * @param carFeatureMapper the car feature mapper
     */
    public CarFeatureDaoImpl(final NamedParameterJdbcTemplate jdbcTemplate,
                             final CarFeatureMapper carFeatureMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.carFeatureMapper = carFeatureMapper;
    }


    /**
     * Gets car features.
     *
     * @param carId the car id
     * @return the car features
     */
    @Override
    public List<CarFeature> getCarFeatures(final int carId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("carId", carId);
        return jdbcTemplate.query(GET_CAR_FEATURES_SQL, parameters,
                carFeatureMapper);
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
        return jdbcTemplate.queryForObject(GET_CAR_FEATURE_SQL,
                parameters, carFeatureMapper);
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
        jdbcTemplate.update(ADD_CAR_FEATURE_SQL, parameters);
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
        jdbcTemplate.update(UPDATE_CAR_FEATURE_SQL, parameters);
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
        jdbcTemplate.update(DELETE_CAR_FEATURE_SQL, parameters);
    }
}
