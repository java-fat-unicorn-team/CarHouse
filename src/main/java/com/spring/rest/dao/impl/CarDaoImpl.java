package com.spring.rest.dao.impl;

import com.spring.rest.dao.CarDao;
import com.spring.rest.model.Car;
import com.spring.rest.model.mappers.CarMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * The type Car dao.
 */
@Repository
public class CarDaoImpl implements CarDao {
    @Value("${get.car}")
    private String GET_CAR_SQL;
    @Value("${get.all.cars}")
    private String GET_ALL_CARS_SQL;
    @Value("${add.car}")
    private String ADD_CAR_SQL;
    @Value("${update.car}")
    private String UPDATE_CAR_SQL;
    @Value("${delete.car}")
    private String DELETE_CAR_SQL;

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final CarMapper carMapper;

    /**
     * Instantiates a new Car dao.
     *
     * @param jdbcTemplate the jdbc template
     * @param carMapper    the car mapper
     */
    public CarDaoImpl(final NamedParameterJdbcTemplate jdbcTemplate,
                      final CarMapper carMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.carMapper = carMapper;
    }

    @Override
    public List<Car> getAllCars() {
        return jdbcTemplate.query(GET_ALL_CARS_SQL, carMapper);
    }

    @Override
    public Car getCar(final int index) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", index);
        return jdbcTemplate.queryForObject(GET_CAR_SQL, parameters, carMapper);
    }

    @Override
    public void addCar(final int year, final int mileage, final int fuelTypeId,
                       final int transmissionId, final int carModelId) {
        Calendar cal = new GregorianCalendar(year, Calendar.JANUARY, 1);
        Date date = cal.getTime();
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("year", date)
                .addValue("mileage", mileage)
                .addValue("fuelType", fuelTypeId)
                .addValue("transmission", transmissionId)
                .addValue("carModel", carModelId);
        jdbcTemplate.update(ADD_CAR_SQL, parameters);
    }

    @Override
    public void updateCar(final int index, final int year, final int mileage,
                          final int fuelTypeId, final int transmissionId,
                          final int carModelId) {
        Calendar cal = new GregorianCalendar(year, Calendar.JANUARY, 1);
        Date date = cal.getTime();
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", index)
                .addValue("year", date)
                .addValue("mileage", mileage)
                .addValue("fuelType", fuelTypeId)
                .addValue("transmission", transmissionId)
                .addValue("carModel", carModelId);
        jdbcTemplate.update(UPDATE_CAR_SQL, parameters);
    }

    @Override
    public void deleteCar(final int index) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", index);
        jdbcTemplate.update(DELETE_CAR_SQL, parameters);
    }
}
