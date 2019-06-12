package com.spring.rest.dao.impl;

import com.spring.rest.dao.CarCharacteristicsDao;
import com.spring.rest.model.CarCharacteristics;
import com.spring.rest.model.mappers.CarCharacteristicsMapper;
import com.spring.rest.model.mappers.ParameterSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The class provides methods to manage CarCharacteristics model.
 * The class stores date in database
 */
@Repository
public class CarCharacteristicsDaoImpl implements CarCharacteristicsDao {
    /**
     * SQL query to get car characteristics.
     */
    @Value("${car.characteristics.get}")
    private String GET_CAR_CHARACTERISTICS_SQL;
    /**
     * SQL query to get cars characteristics.
     */
    @Value("${cars.characteristics.get}")
    private String GET_CARS_CHARACTERISTICS_SQL;
    /**
     * SQL query to add car characteristics.
     */
    @Value("${car.characteristics.add}")
    private String ADD_CAR_CHARACTERISTICS_SQL;
    /**
     * SQL query to update car characteristics.
     */
    @Value("${car.characteristics.update}")
    private String UPDATE_CAR_CHARACTERISTICS_SQL;
    /**
     * SQL query to get car characteristics.
     */
    @Value("${car.characteristics.delete}")
    private String DELETE_CAR_CHARACTERISTICS_SQL;

    /**
     * named parameter JDBC template.
     */
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    /**
     * mapper to get CarCharacteristics object.
     */
    private final CarCharacteristicsMapper carCharacteristicsMapper;
    /**
     * class is used to get parameters for sql query.
     */
    private final ParameterSource parameterSource;
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger(CarCharacteristicsDaoImpl.class);

    /**
     * Instantiates a new CarCharacteristics dao.
     *
     * @param namedParameterJdbcTemplate the jdbc template
     * @param carCharacteristicsMapper   the car mapper
     * @param parameterSource            the parameter source
     */
    @Autowired
    public CarCharacteristicsDaoImpl(final NamedParameterJdbcTemplate namedParameterJdbcTemplate,
            final CarCharacteristicsMapper carCharacteristicsMapper, final ParameterSource parameterSource) {
        LOGGER.debug("CarCharacteristicsDaoImpl was created");
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.carCharacteristicsMapper = carCharacteristicsMapper;
        this.parameterSource = parameterSource;
    }

    /**
     * Gets cars characteristics.
     *
     * @return the list of cars characteristics
     */
    @Override
    public List<CarCharacteristics> getCarsCharacteristics() {
        LOGGER.info("method getCarsCharacteristics was called");
        return namedParameterJdbcTemplate.query(GET_CARS_CHARACTERISTICS_SQL, carCharacteristicsMapper);
    }

    /**
     * Gets car characteristics.
     *
     * @param index the index
     * @return the car characteristics
     */
    @Override
    public CarCharacteristics getCarCharacteristics(final int index) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", index);
        LOGGER.info("method getCarCharacteristics with parameter: {} was called", index);
        return namedParameterJdbcTemplate.queryForObject(GET_CAR_CHARACTERISTICS_SQL, parameters,
                carCharacteristicsMapper);
    }

    /**
     * Add car characteristics.
     *
     * @param car the car
     */
    @Override
    public void addCarCharacteristics(final CarCharacteristics car) {
        LOGGER.info("method addCarCharacteristics with parameter: {}", car);
        namedParameterJdbcTemplate.update(ADD_CAR_CHARACTERISTICS_SQL,
                parameterSource.getCarCharacteristicsParameters(car));
    }

    /**
     * Update car characteristics.
     *
     * @param car the car
     */
    @Override
    public void updateCarCharacteristics(final CarCharacteristics car) {
        LOGGER.info("method updateCarCharacteristics with parameter: {}", car);
        namedParameterJdbcTemplate.update(UPDATE_CAR_CHARACTERISTICS_SQL,
                parameterSource.getCarCharacteristicsParameters(car));
    }

    /**
     * Delete car characteristics.
     *
     * @param index the index
     */
    @Override
    public void deleteCarCharacteristics(final int index) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", index);
        LOGGER.info("method deleteCarCharacteristics with parameter: {} was called", index);
        namedParameterJdbcTemplate.update(DELETE_CAR_CHARACTERISTICS_SQL, parameters);
    }
}
