package com.spring.rest.dao.impl;

import com.spring.rest.dao.CarMakeDao;
import com.spring.rest.model.CarMake;
import com.spring.rest.model.mappers.CarMakeMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The class provides methods to get CarMake model.
 * The class stores date in database
 */
@Repository
public class CarMakeDaoImpl implements CarMakeDao {
    /**
     * SQL query to get car make.
     */
    @Value("${car.make.get}")
    private String GET_CAR_MAKE_SQL;
    /**
     * SQL query to get car makes.
     */
    @Value("${car.makes.get}")
    private String GET_CARS_MAKES_SQL;

    /**
     * named parameter JDBC template.
     */
    private final NamedParameterJdbcTemplate jdbcTemplate;
    /**
     * mapper to get CarMake object.
     */
    private final CarMakeMapper carMakeMapper;

    /**
     * Instantiates a new Car make dao.
     *
     * @param jdbcTemplate  the jdbc template
     * @param carMakeMapper the car make mapper
     */
    public CarMakeDaoImpl(final NamedParameterJdbcTemplate jdbcTemplate,
                          final CarMakeMapper carMakeMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.carMakeMapper = carMakeMapper;
    }

    /**
     * Gets car makes.
     *
     * @return the car makes
     */
    @Override
    public List<CarMake> getCarMakes() {
        return jdbcTemplate.query(GET_CARS_MAKES_SQL, carMakeMapper);
    }

    /**
     * Gets car make.
     *
     * @param index the index
     * @return the car make
     */
    @Override
    public CarMake getCarMake(final int index) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", index);
        return jdbcTemplate.queryForObject(GET_CAR_MAKE_SQL, parameters,
                carMakeMapper);
    }
}
