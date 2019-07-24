package com.carhouse.dao.impl;

import com.carhouse.dao.CarDao;
import com.carhouse.dao.CarFeatureDao;
import com.carhouse.dao.CarHasCarFeatureDao;
import com.carhouse.dao.mappers.CarMapper;
import com.carhouse.dao.mappers.ParameterSource;
import com.carhouse.model.Car;
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
 * It is realisation of CarDao interface
 *
 * @author Katuranau Maksimilyan
 * @see CarDao
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

    private final CarFeatureDao carFeatureDao;
    private final CarHasCarFeatureDao carHasCarFeatureDao;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final CarMapper carMapper;

    private final ParameterSource parameterSource;

    private static final Logger LOGGER = LogManager.getLogger(CarDaoImpl.class);

    /**
     * Instantiates a new Car dao.
     *
     * @param namedParameterJdbcTemplate for connection with database
     * @param carMapper                  mapper to get Car object
     * @param parameterSource            the class is used to get parameters for sql query
     * @param carFeatureDao              the car feature dao to adds car features to car
     * @param carHasCarFeatureDao        the car has car feature dao which provides methods to manage references
     *                                   between car and car features.
     */
    @Autowired
    public CarDaoImpl(final NamedParameterJdbcTemplate namedParameterJdbcTemplate, final CarMapper carMapper,
                      final ParameterSource parameterSource, final CarFeatureDao carFeatureDao,
                      final CarHasCarFeatureDao carHasCarFeatureDao) {
        LOGGER.debug("CarDaoImpl was created");
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.carMapper = carMapper;
        this.parameterSource = parameterSource;
        this.carFeatureDao = carFeatureDao;
        this.carHasCarFeatureDao = carHasCarFeatureDao;
    }

    /**
     * Gets all cars from database.
     *
     * @return the list of cars
     */
    @Override
    public List<Car> getCars() {
        LOGGER.debug("method getCars");
        List<Car> cars = namedParameterJdbcTemplate.query(GET_LIST_CARS_SQL, carMapper);
        cars.forEach(car -> {
            car.setCarFeatureList(carFeatureDao.getCarFeatures(car.getCarId()));
        });
        return cars;
    }

    /**
     * Gets one car by id.
     *
     * @param id the car id
     * @return the car
     */
    @Override
    public Car getCar(final int id) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        LOGGER.debug("method getCar with parameter: [{}]", id);
        Car car = namedParameterJdbcTemplate.queryForObject(GET_CAR_SQL, parameters, carMapper);
        car.setCarFeatureList(carFeatureDao.getCarFeatures(car.getCarId()));
        return car;
    }

    /**
     * Add car.
     * The method adds car to the end of list in database
     *
     * @param car the car model
     * @return car id
     */
    @Override
    public Integer addCar(final Car car) {
        LOGGER.debug("method addCar with parameter: [{}]", car);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(ADD_CAR_SQL,
                parameterSource.getCarParameters(car), keyHolder);
        int carId = keyHolder.getKey().intValue();
        car.getCarFeatureList().forEach(carFeature -> {
            carHasCarFeatureDao.addCarFeature(carId, carFeature.getCarFeatureId());
        });
        return carId;
    }

    /**
     * Update car.
     * Gets car id from car object and rewrite the car in database
     * <p>
     * To correctly change references the method first remove old references and then add new
     *
     * @param car the car model
     */
    @Override
    public void updateCar(final Car car) {
        LOGGER.debug("method updateCar with parameter: [{}]", car);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(UPDATE_CAR_SQL,
                parameterSource.getCarParameters(car), keyHolder);
        carHasCarFeatureDao.deleteAllCarFeatures(car.getCarId());
        car.getCarFeatureList().forEach(carFeature -> {
            carHasCarFeatureDao.addCarFeature(car.getCarId(), carFeature.getCarFeatureId());
        });
    }

    /**
     * Delete car by id.
     *
     * @param id the car id
     */
    @Override
    public void deleteCar(final int id) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        LOGGER.debug("method deleteCar with parameter: [{}]", id);
        namedParameterJdbcTemplate.update(DELETE_CAR_SQL, parameters);
    }
}
