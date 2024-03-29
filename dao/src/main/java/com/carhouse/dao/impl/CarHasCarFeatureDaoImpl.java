package com.carhouse.dao.impl;

import com.carhouse.dao.CarHasCarFeatureDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 * The class provides methods to manage references between car and CarFeature models.
 * The class stores date in database
 * It is realisation of CarHasCarFeatureDao interface
 *
 * @author Katuranau Maksimilyan
 * @see CarHasCarFeatureDao
 */
@Repository
public class CarHasCarFeatureDaoImpl implements CarHasCarFeatureDao {

    @Value("${car.feature.add.to.car}")
    private String ADD_CAR_FEATURE_TO_CAR_SQL;

    @Value("${car.feature.delete.from.car}")
    private String DELETE_CAR_FEATURE_FROM_CAR_SQL;

    @Value("${car.feature.list.delete.from.car}")
    private String DELETE_CAR_FEATURE_LIST_FROM_CAR_SQL;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final Logger LOGGER = LogManager.getLogger(CarHasCarFeatureDaoImpl.class);

    /**
     * Instantiates a new Car has car feature.
     *
     * @param namedParameterJdbcTemplate for connection with database
     */
    @Autowired
    public CarHasCarFeatureDaoImpl(final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * add car feature to car by id.
     *
     * @param carId        car id
     * @param carFeatureId id of car feature to add
     */
    @Override
    public void addCarFeature(final int carId, final int carFeatureId) {
        LOGGER.debug("method addCarFeature with parameters: [{}, {}]", carId, carFeatureId);
        namedParameterJdbcTemplate.update(ADD_CAR_FEATURE_TO_CAR_SQL, getParameters(carId, carFeatureId));
    }

    /**
     * delete car feature from car by id.
     *
     * @param carId        car id
     * @param carFeatureId id of car feature to delete
     */
    @Override
    public void deleteCarFeature(final int carId, final int carFeatureId) {
        LOGGER.debug("method deleteCarFeature with parameters: [{}, {}]", carId, carFeatureId);
        namedParameterJdbcTemplate.update(DELETE_CAR_FEATURE_FROM_CAR_SQL, getParameters(carId, carFeatureId));
    }

    /**
     * delete all references between car with provided id and car features.
     *
     * @param carId id of car for which to delete all references
     */
    @Override
    public void deleteAllCarFeatures(final int carId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("carId", carId);
        LOGGER.debug("method deleteCarFeature with parameter: [{}]", carId);
        namedParameterJdbcTemplate.update(DELETE_CAR_FEATURE_LIST_FROM_CAR_SQL, parameters);
    }

    /**
     * method to set parameters to SqlParameterSource object.
     *
     * @param carId        car id
     * @param carFeatureId car feature id
     * @return SqlParameterSource object which contains parameters for SQL query
     */
    private SqlParameterSource getParameters(final int carId, final int carFeatureId) {

        return new MapSqlParameterSource()
                .addValue("carId", carId)
                .addValue("carFeatureId", carFeatureId);
    }
}
