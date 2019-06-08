package com.spring.rest.dao.impl;

import com.spring.rest.dao.CarSaleDao;
import com.spring.rest.model.CarSale;
import com.spring.rest.model.mappers.CarSaleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The type Car sale dao.
 */
@Repository
public class CarSaleDaoImpl implements CarSaleDao {
    @Value("${get.car.sale}")
    private String GET_CAR_SALE_SQL;
    @Value("${get.all.car.sales}")
    private String GET_ALL_CAR_SALES_SQL;
    @Value("${add.car.sale}")
    private String ADD_CAR_SALE_SQL;
    @Value("${update.car.sale}")
    private String UPDATE_CAR_SALE_SQL;
    @Value("${delete.car.sale}")
    private String DELETE_CAR_SALE_SQL;

    /**
     * JdbcTemplate.
     */
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final CarSaleMapper carSaleMapper;

    /**
     * Instantiates a new Car sale dao.
     *
     * @param jdbcTemplate  the jdbc template
     * @param carSaleMapper the car sale mapper
     */
    @Autowired
    public CarSaleDaoImpl(final NamedParameterJdbcTemplate jdbcTemplate,
                          final CarSaleMapper carSaleMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.carSaleMapper = carSaleMapper;
    }

    @Override
    public List<CarSale> getAllCarSales() {
        return jdbcTemplate.query(GET_ALL_CAR_SALES_SQL, carSaleMapper);
    }

    @Override
    public CarSale getCarSale(final int index) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", index);
        return jdbcTemplate.queryForObject(GET_CAR_SALE_SQL, parameters, carSaleMapper);
    }

    @Override
    public void addCarSale(final BigDecimal price, final int userId,
                           final int carId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("price", price)
                .addValue("date", new Date(System.currentTimeMillis()))
                .addValue("userId", userId)
                .addValue("carId", carId);
        jdbcTemplate.update(ADD_CAR_SALE_SQL, parameters);
    }

    @Override
    public void updateCarSale(final int index, final BigDecimal price,
                              final int userId, final int carId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", index)
                .addValue("price", price)
                .addValue("userId", userId)
                .addValue("carId", carId);
        jdbcTemplate.update(UPDATE_CAR_SALE_SQL, parameters);
    }

    @Override
    public void deleteCarSale(final int index) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", index);
        jdbcTemplate.update(DELETE_CAR_SALE_SQL, parameters);
    }
}
