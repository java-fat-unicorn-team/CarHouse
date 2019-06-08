package com.spring.rest.dao.impl;

import com.spring.rest.dao.CarModelDao;
import com.spring.rest.model.CarModel;
import com.spring.rest.model.mappers.CarModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarModelDaoImpl implements CarModelDao {
    @Value("${get.car.model}")
    private String GET_CAR_MODEL_SQL;
    @Value("${get.all.car.model}")
    private String GET_ALL_CAR_MODELS_SQL;

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final CarModelMapper carModelMapper;

    public CarModelDaoImpl(NamedParameterJdbcTemplate jdbcTemplate, CarModelMapper carModelMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.carModelMapper = carModelMapper;
    }

    @Override
    public List<CarModel> getAllCarModels() {
        return jdbcTemplate.query(GET_ALL_CAR_MODELS_SQL, carModelMapper);
    }

    @Override
    public CarModel getCarModel(int index) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", index);
        return jdbcTemplate.queryForObject(GET_CAR_MODEL_SQL, parameters, carModelMapper);
    }
}
