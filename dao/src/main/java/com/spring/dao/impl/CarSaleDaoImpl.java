package com.spring.dao.impl;

import com.spring.dao.CarSaleDao;
import com.spring.model.CarSale;
import com.spring.model.mappers.CarSaleMapper;
import com.spring.model.mappers.ParameterSource;
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
 * The class provides methods to manage CarSale model.
 * The class stores date in database
 * @author Katuranau Maksimilyan
 */
@Repository
public class CarSaleDaoImpl implements CarSaleDao {
    /**
     * SQL query to get car sale.
     */
    @Value("${car.sale.get}")
    private String GET_CAR_SALE_SQL;
    /**
     * SQL query to get list of car sales.
     */
    @Value("${car.sales.list.get}")
    private String GET_LIST_CAR_SALES_SQL;
    /**
     * SQL query to add car sale.
     */
    @Value("${car.sale.add}")
    private String ADD_CAR_SALE_SQL;
    /**
     * SQL query to update car sale.
     */
    @Value("${car.sale.update}")
    private String UPDATE_CAR_SALE_SQL;
    /**
     * SQL query to delete car sale.
     */
    @Value("${car.sale.delete}")
    private String DELETE_CAR_SALE_SQL;

    /**
     * named parameter JDBC template.
     */
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    /**
     * mapper to get CarSale object.
     */
    private final CarSaleMapper carSaleMapper;
    /**
     * class is used to get parameters for sql query.
     */
    private final ParameterSource parameterSource;
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger(CarSaleDaoImpl.class);

    /**
     * Instantiates a new Car sale dao.
     *
     * @param namedParameterJdbcTemplate the named parameter jdbc template
     * @param carSaleMapper              the car sale mapper
     * @param parameterSource            the parameter source
     */
    @Autowired
    public CarSaleDaoImpl(final NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                          final CarSaleMapper carSaleMapper, final ParameterSource parameterSource) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.carSaleMapper = carSaleMapper;
        this.parameterSource = parameterSource;
    }

    /**
     * Gets car sales.
     *
     * @return the car sales
     */
    @Override
    public List<CarSale> getCarSales() {
        LOGGER.debug("method getCarSales was called");
        return namedParameterJdbcTemplate.query(GET_LIST_CAR_SALES_SQL, carSaleMapper);
    }

    /**
     * Gets car sale.
     *
     * @param index the index
     * @return the list of car sale
     */
    @Override
    public CarSale getCarSale(final int index) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", index);
        LOGGER.debug("method getCarSale with parameter: {} was called", index);
        return namedParameterJdbcTemplate.queryForObject(GET_CAR_SALE_SQL, parameters, carSaleMapper);
    }

    /**
     * Add car sale.
     *
     * @param carSale the car sale
     * @return car sale id
     */
    @Override
    public Integer addCarSale(final CarSale carSale) {
        LOGGER.debug("method addCarSale with parameter: {}", carSale);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(ADD_CAR_SALE_SQL, parameterSource.getCarSaleParameters(carSale), keyHolder,
                new String[] {"car_sale_id"});
        return keyHolder.getKey().intValue();
    }

    /**
     * Update car sale.
     *
     * @param carSale the car sale
     */
    @Override
    public void updateCarSale(final CarSale carSale) {
        LOGGER.debug("method updateCarSale with parameter: {}", carSale);
        namedParameterJdbcTemplate.update(UPDATE_CAR_SALE_SQL, parameterSource.getCarSaleParameters(carSale));
    }

    /**
     * Delete car sale.
     *
     * @param index the index
     */
    @Override
    public void deleteCarSale(final int index) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", index);
        LOGGER.debug("method deleteCarSale with parameter: {} was called", index);
        namedParameterJdbcTemplate.update(DELETE_CAR_SALE_SQL, parameters);
    }
}
