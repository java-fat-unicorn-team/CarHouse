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
 * The type Car feature dao.
 */
@Repository
public class CarFeatureDaoImpl implements CarFeatureDao {
    @Value("${get.car.feature}")
    private String GET_CAR_FEATURE_SQL;
    @Value("${get.all.car.features}")
    private String GET_ALL_CAR_FEATURES_SQL;
    @Value("${add.car.feature}")
    private String ADD_CAR_FEATURE_SQL;
    @Value("${update.car.feature}")
    private String UPDATE_CAR_FEATURE_SQL;
    @Value("${delete.car.feature}")
    private String DELETE_CAR_FEATURE_SQL;

    private final NamedParameterJdbcTemplate jdbcTemplate;
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


    @Override
    public List<CarFeature> getAllCarFeatures(final int carId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("carId", carId);
        return jdbcTemplate.query(GET_ALL_CAR_FEATURES_SQL, parameters,
                carFeatureMapper);
    }

    @Override
    public CarFeature getCarFeature(final int index) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", index);
        return jdbcTemplate.queryForObject(GET_CAR_FEATURE_SQL,
                parameters, carFeatureMapper);
    }

    @Override
    public void addCarFeature(final String carFeature, final int carId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("carFeature", carFeature)
                .addValue("carId", carId);
        jdbcTemplate.update(ADD_CAR_FEATURE_SQL, parameters);
    }

    @Override
    public void updateCarFeature(final String carFeature, final int carFeatureId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("carFeature", carFeature)
                .addValue("id", carFeatureId);
        jdbcTemplate.update(UPDATE_CAR_FEATURE_SQL, parameters);
    }

    @Override
    public void deleteCarFeature(final int index) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", index);
        jdbcTemplate.update(DELETE_CAR_FEATURE_SQL, parameters);
    }
}
