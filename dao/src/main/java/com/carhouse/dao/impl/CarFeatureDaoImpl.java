package com.carhouse.dao.impl;

import com.carhouse.dao.CarFeatureDao;
import com.carhouse.dao.mappers.CarFeatureMapper;
import com.carhouse.model.CarFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The class provides method to get CarFeature models.
 * The class stores date in database
 * It is realisation of CarFeatureDao interface
 *
 * @author Katuranau Maksimilyan
 * @see CarFeatureDao
 */
@Repository
public class CarFeatureDaoImpl implements CarFeatureDao {
    /**
     * SQL query to get list of car features.
     */
    @Value("${car.features.list.get}")
    private String GET_LIST_CAR_FEATURES_SQL;
    /**
     * SQL query to get list of all features.
     */
    @Value("${features.list.get}")
    private String GET_LIST_FEATURES_SQL;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final CarFeatureMapper carFeatureMapper;

    private static final Logger LOGGER = LogManager.getLogger(CarFeatureDaoImpl.class);

    /**
     * Instantiates a new Car feature dao.
     *
     * @param namedParameterJdbcTemplate for connection with database
     * @param carFeatureMapper           mapper to get CarFeature object.
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

    @Override
    public List<CarFeature> getAllFeatures() {
        LOGGER.debug("method getAllFeatures");
        return namedParameterJdbcTemplate.query(GET_LIST_FEATURES_SQL, carFeatureMapper);
    }
}
