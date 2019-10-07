package com.carhouse.dao.impl;

import com.carhouse.dao.CarFeatureDao;
import com.carhouse.dao.CarSaleDao;
import com.carhouse.dao.conditions.DefaultConditions;
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

    private final CarFeatureDao carFeatureDao;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final CarSaleMapper carSaleMapper;
    private final CarSaleDtoMapper carSaleDtoMapper;

    private final ParameterSource parameterSource;

    private static final Logger LOGGER = LogManager.getLogger(CarSaleDaoImpl.class);

    /**
     * Instantiates a new Car sale dao.
     *
     * @param carFeatureDao              the car feature dao to adds car features to car
     * @param carSaleDtoMapper           mapper to get CarSaleDto object
     * @param carSaleMapper              mapper to get CarSale object
     * @param parameterSource            class is used to get parameters for sql query
     * @param namedParameterJdbcTemplate for connection with database
     */
    @Autowired
    public CarSaleDaoImpl(final CarFeatureDao carFeatureDao, final CarSaleDtoMapper carSaleDtoMapper,
                          final CarSaleMapper carSaleMapper, final ParameterSource parameterSource,
                          final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.carFeatureDao = carFeatureDao;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.carSaleMapper = carSaleMapper;
        this.carSaleDtoMapper = carSaleDtoMapper;
        this.parameterSource = parameterSource;
    }

    /**
     * Gets car sales.
     * Set conditions value from map to sql query or use default value if parameter doesn't exist
     * Return list of dto object without redundant information
     *
     * @param conditionParams the request params
     * @return the list of car sales
     */
    @Override
    public List<CarSaleDto> getListCarSales(final Map<String, String> conditionParams) {
        LOGGER.debug("method getCarSalesDto");
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        for (DefaultConditions condition : DefaultConditions.values()) {
            parameters.addValue(condition.getKey(), conditionParams.getOrDefault(condition.getKey(),
                    condition.getDefaultValue()));
        }
        return namedParameterJdbcTemplate.query(GET_LIST_CAR_SALES_SQL, parameters, carSaleDtoMapper);
    }

    /**
     * Gets car sale by id.
     * Use carFeatureDao to set car features
     *
     * @param carSaleId the car sale id
     * @return the list of car sale
     */
    @Override
    public CarSale getCarSale(final int carSaleId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", carSaleId);
        LOGGER.debug("method getCarSale with parameter: [{}]", carSaleId);
        CarSale carSale = namedParameterJdbcTemplate.queryForObject(GET_CAR_SALE_SQL, parameters, carSaleMapper);
        carSale.getCar().setCarFeatureList(carFeatureDao.getCarFeatures(carSale.getCar().getCarId()));
        return carSale;
    }

    /**
     * Add car sale.
     * Database generate new id and take all data from carSale object
     * Adds car sale advertisement to the end of list in database
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
