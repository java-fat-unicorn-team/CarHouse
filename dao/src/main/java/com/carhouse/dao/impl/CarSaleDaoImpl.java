package com.carhouse.dao.impl;

import com.carhouse.dao.CarSaleDao;
import com.carhouse.dao.mappers.CarSaleMapper;
import com.carhouse.dao.mappers.ParameterSource;
import com.carhouse.model.CarSale;
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
 * It is realisation of CarSaleDao interface
 *
 * @author Katuranau Maksimilyan
 * @see CarSaleDao
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
     * @param parameterSource            the class which provides parameters for sql query
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
        LOGGER.debug("method getCarSales");
        return namedParameterJdbcTemplate.query(GET_LIST_CAR_SALES_SQL, carSaleMapper);
    }

    /**
     * Gets car sale by id.
     *
     * @param carSaleId the car sale id
     * @return the list of car sale
     */
    @Override
    public CarSale getCarSale(final int carSaleId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", carSaleId);
        LOGGER.debug("method getCarSale with parameter: [{}]", carSaleId);
        return namedParameterJdbcTemplate.queryForObject(GET_CAR_SALE_SQL, parameters, carSaleMapper);
    }

    /**
     * Add car sale.
     * Database generate new id and take all data from carSale object
     * Ads car sale advertisement to the end of list in database
     *
     * @param carSale the car sale
     * @return car sale id
     */
    @Override
    public Integer addCarSale(final CarSale carSale) {
        LOGGER.debug("method addCarSale with parameter: [{}]", carSale);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(ADD_CAR_SALE_SQL, parameterSource.getCarSaleParameters(carSale), keyHolder,
                new String[]{"car_sale_id"});
        return keyHolder.getKey().intValue();
    }

    /**
     * Update car sale.
     * Gets car sale id from carSale object and rewrite the carSale in database
     *
     * @param carSale the car sale
     */
    @Override
    public void updateCarSale(final CarSale carSale) {
        LOGGER.debug("method updateCarSale with parameter: [{}]", carSale);
        namedParameterJdbcTemplate.update(UPDATE_CAR_SALE_SQL, parameterSource.getCarSaleParameters(carSale));
    }

    /**
     * Delete car sale by id.
     *
     * @param carSaleId the car sale id
     */
    @Override
    public void deleteCarSale(final int carSaleId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", carSaleId);
        LOGGER.debug("method deleteCarSale with parameter: [{}]", carSaleId);
        namedParameterJdbcTemplate.update(DELETE_CAR_SALE_SQL, parameters);
    }
}
