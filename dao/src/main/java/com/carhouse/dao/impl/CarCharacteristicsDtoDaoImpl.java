package com.carhouse.dao.impl;

import com.carhouse.dao.CarCharacteristicsDtoDao;
import com.carhouse.dao.mappers.CarCharacteristicsDtoMapper;
import com.carhouse.model.dto.CarCharacteristicsDto;
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

    private final CarCharacteristicsDtoMapper carCharacteristicsDtoMapper;
    private static final Logger LOGGER = LogManager.getLogger(CarCharacteristicsDtoDaoImpl.class);

    /**
     * Instantiates a new Car characteristics dto dao.
     *
     * @param namedParameterJdbcTemplate  the named parameter jdbc template
     * @param carCharacteristicsDtoMapper mapper to get CarCharacteristicsDto object
     */
    @Autowired
    public CarCharacteristicsDtoDaoImpl(final NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                                        final CarCharacteristicsDtoMapper carCharacteristicsDtoMapper) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.carCharacteristicsDtoMapper = carCharacteristicsDtoMapper;
    }


    /**
     * Gets car characteristics.
     * It is used to reduce the count of sql queries
     *
     * @return the car characteristics
     */
    @Override
    public CarCharacteristicsDto getCarCharacteristics() {
        LOGGER.debug("method getCarCharacteristics");
        return namedParameterJdbcTemplate.queryForObject(GET_CAR_CHARACTERISTICS_SQL, new HashMap<>(),
                carCharacteristicsDtoMapper);
    }
}
