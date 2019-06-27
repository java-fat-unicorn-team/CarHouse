package com.carhouse.dao.impl;

import com.carhouse.dao.CarDao;
import com.carhouse.model.Car;
import com.carhouse.dao.mappers.CarMapper;
import com.carhouse.dao.mappers.ParameterSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * The class provides methods to manage Car model.
 * The class stores date in database
 * @author Katuranau Maksimilyan
 */
@Repository
public class CarDaoImpl implements CarDao {
    /**
     * SQL query to get car.
     */
    @Value("${car.get}")
    private String GET_CAR_SQL;
    /**
     * SQL query to get list of cars.
     */
    @Value("${cars.list.get}")
    private String GET_LIST_CARS_SQL;
    /**
     * SQL query to add car.
     */
    @Value("${car.add}")
    private String ADD_CAR_SQL;
    /**
     * SQL query to update car.
     */
    @Value("${car.update}")
    private String UPDATE_CAR_SQL;
    /**
     * SQL query to get car.
     */
    @Value("${car.delete}")
    private String DELETE_CAR_SQL;

    /**
     * named parameter JDBC template.
     */
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    /**
     * mapper to get Car object.
     */
    private final CarMapper carMapper;
    /**
     * class is used to get parameters for sql query.
     */
    private final ParameterSource parameterSource;
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger(CarDaoImpl.class);

    /**
     * Instantiates a new Car dao.
     *
     * @param namedParameterJdbcTemplate the jdbc template
     * @param carMapper   the car mapper
     * @param parameterSource            the parameter source
     */
    @Autowired
    public CarDaoImpl(final NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                      final CarMapper carMapper,
                      final ParameterSource parameterSource) {
        LOGGER.debug("CarDaoImpl was created");
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.carMapper = carMapper;
        this.parameterSource = parameterSource;
    }

    /**
     * Gets cars.
     *
     * @return the list of cars
     */
    @Override
    public List<Car> getCars() {
        LOGGER.debug("method getCars was called");
        return namedParameterJdbcTemplate.query(GET_LIST_CARS_SQL, carMapper);
    }

    /**
     * Gets car.
     *
     * @param index the index
     * @return the car
     */
    @Override
    public Car getCar(final int index) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", index);
        LOGGER.debug("method getCar with parameter: {} was called", index);
        return namedParameterJdbcTemplate.queryForObject(GET_CAR_SQL, parameters,
                carMapper);
    }

    /**
     * Add car.
     *
     * @param car the car
     * @return car id
     */
    @Override
    public Integer addCar(final Car car) {
        LOGGER.debug("method addCar with parameter: {}", car);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(ADD_CAR_SQL,
                parameterSource.getCarParameters(car), keyHolder);
        return keyHolder.getKey().intValue();
    }

    /**
     * Update car.
     *
     * @param car the car
     */
    @Override
    public void updateCar(final Car car) {
        LOGGER.debug("method updateCar with parameter: {}", car);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(UPDATE_CAR_SQL,
                parameterSource.getCarParameters(car), keyHolder);
    }

    /**
     * Delete car.
     *
     * @param index the index
     */
    @Override
    public void deleteCar(final int index) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", index);
        LOGGER.debug("method deleteCar with parameter: {} was called", index);
        namedParameterJdbcTemplate.update(DELETE_CAR_SQL, parameters);
    }
}
