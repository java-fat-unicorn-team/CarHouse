package com.carhouse.dao.impl;

import com.carhouse.dao.CarCharacteristicsDtoDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

/**
 * The type Car characteristics dto dao.
 */
@Repository
public class CarCharacteristicsDtoDaoImpl implements CarCharacteristicsDtoDao {

    @Value("${car.characteristics.get}")
    private String GET_CAR_CHARACTERISTICS_SQL;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final Logger LOGGER = LogManager.getLogger(CarCharacteristicsDtoDaoImpl.class);

    /**
     * Instantiates a new Car characteristics dto dao.
     *
     * @param namedParameterJdbcTemplate the named parameter jdbc template
     */
    @Autowired
    public CarCharacteristicsDtoDaoImpl(final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    /**
     * Gets car characteristics in JSON format.
     * It is used to reduce the count of sql queries
     *
     * @return the car characteristics in json format
     */
    @Override
    public String getCarCharacteristics() {
        LOGGER.debug("method getCarCharacteristics");
        return namedParameterJdbcTemplate.queryForObject(GET_CAR_CHARACTERISTICS_SQL, new HashMap<>(), String.class);
    }
}
