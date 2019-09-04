package com.carhouse.dao.impl;

import com.carhouse.dao.CarSaleDao;
import com.carhouse.dao.builders.CarSaleSqlBuilder;
import com.carhouse.dao.builders.models.Condition;
import com.carhouse.dao.mappers.CarSaleDtoMapper;
import com.carhouse.dao.mappers.CarSaleMapper;
import com.carhouse.dao.mappers.ParameterSource;
import com.carhouse.model.CarSale;
import com.carhouse.model.dto.CarSaleDto;
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
import java.util.Map;


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

    @Value("${car.sale.get}")
    private String GET_CAR_SALE_SQL;

    @Value("${car.sales.list.get}")
    private String GET_LIST_CAR_SALES_SQL;

    @Value("${car.sale.add}")
    private String ADD_CAR_SALE_SQL;

    @Value("${car.sale.update}")
    private String UPDATE_CAR_SALE_SQL;

    @Value("${car.sale.delete}")
    private String DELETE_CAR_SALE_SQL;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final CarSaleMapper carSaleMapper;
    private final CarSaleDtoMapper carSaleDtoMapper;

    private final CarSaleSqlBuilder carSaleSqlBuilder;
    private final ParameterSource parameterSource;

    private static final Logger LOGGER = LogManager.getLogger(CarSaleDaoImpl.class);

    /**
     * Instantiates a new Car sale dao.
     *
     * @param namedParameterJdbcTemplate for connection with database
     * @param carSaleMapper              mapper to get CarSale object
     * @param carSaleDtoMapper           mapper to get CarSaleDto object
     * @param carSaleSqlBuilder          the car sale sql builder
     * @param parameterSource            class is used to get parameters for sql query
     */
    @Autowired
    public CarSaleDaoImpl(final NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                          final CarSaleMapper carSaleMapper, final CarSaleDtoMapper carSaleDtoMapper,
                          final CarSaleSqlBuilder carSaleSqlBuilder, final ParameterSource parameterSource) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.carSaleMapper = carSaleMapper;
        this.carSaleDtoMapper = carSaleDtoMapper;
        this.carSaleSqlBuilder = carSaleSqlBuilder;
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
     * Gets car sales dto.
     * Create conditions based on the request params using CarSaleSqlBuilder object
     *
     * @param conditionParams the request params
     * @return the car sales dto
     */
    @Override
    public List<CarSaleDto> getCarSalesDto(final Map<String, String> conditionParams) {
        LOGGER.debug("method getCarSalesDto");
        List<Condition> conditionList = carSaleSqlBuilder
                .buildConditionList(conditionParams);
        return namedParameterJdbcTemplate
                .query(carSaleSqlBuilder.buildSqlQuery(conditionList), carSaleDtoMapper);
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
     * @return true if car sale was updated and false if there is not such car sale in database
     */
    @Override
    public boolean updateCarSale(final CarSale carSale) {
        LOGGER.debug("method updateCarSale with parameter: [{}]", carSale);
        return namedParameterJdbcTemplate.update(UPDATE_CAR_SALE_SQL,
                parameterSource.getCarSaleParameters(carSale)) == 1;
    }

    /**
     * Delete car sale by id.
     *
     * @param carSaleId the car sale id
     * @return true if car sale was deleted and false if there is not such car sale in database
     */
    @Override
    public boolean deleteCarSale(final int carSaleId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", carSaleId);
        LOGGER.debug("method deleteCarSale with parameter: [{}]", carSaleId);
        return namedParameterJdbcTemplate.update(DELETE_CAR_SALE_SQL, parameters) == 1;
    }
}
