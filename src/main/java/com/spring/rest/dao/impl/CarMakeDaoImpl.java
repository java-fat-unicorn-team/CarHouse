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
 * The type Car make dao.
 */
@Repository
public class CarMakeDaoImpl implements CarMakeDao {
    @Value("${get.car.make}")
    private String GET_CAR_MAKE_SQL;
    @Value("${get.all.car.makes}")
    private String GET_ALL_CAR_MAKES_SQL;

    private final NamedParameterJdbcTemplate jdbcTemplate;
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

    @Override
    public List<CarMake> getAllCarMakes() {
        return jdbcTemplate.query(GET_ALL_CAR_MAKES_SQL, carMakeMapper);
    }

    @Override
    public CarMake getCarMake(final int index) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", index);
        return jdbcTemplate.queryForObject(GET_CAR_MAKE_SQL, parameters,
                carMakeMapper);
    }
}
