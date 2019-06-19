package com.spring.rest.model.mappers;

import com.spring.rest.model.CarCharacteristics;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Used to create CarCharacteristics from data obtained from database.
 * @author Katuranau Maksimilyan
 */
@Component
public class CarCharacteristicsMapper implements RowMapper<CarCharacteristics> {
    /**
     * The constant CAR_ID.
     */
    public static final String CAR_ID = "car_id";
    /**
     * The constant YEAR.
     */
    public static final String YEAR = "year";
    /**
     * The constant MILEAGE.
     */
    public static final String MILEAGE = "mileage";
    /**
     * mapper to get FuelType object.
     */
    private FuelTypeMapper fuelTypeMapper;
    /**
     * mapper to get Transmission object.
     */
    private TransmissionMapper transmissionMapper;
    /**
     * mapper to get CarModel object.
     */
    private CarModelMapper carModelMapper;
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger(CarCharacteristicsMapper.class);

    /**
     * Instantiates a new CarCharacteristics mapper.
     *
     * @param fuelTypeMapper     the fuel type mapper
     * @param transmissionMapper the transmission mapper
     * @param carModelMapper     the car model mapper
     */
    @Autowired
    public CarCharacteristicsMapper(final FuelTypeMapper fuelTypeMapper, final TransmissionMapper transmissionMapper,
                                    final CarModelMapper carModelMapper) {
        this.fuelTypeMapper = fuelTypeMapper;
        this.transmissionMapper = transmissionMapper;
        this.carModelMapper = carModelMapper;
    }


    @Override
    public CarCharacteristics mapRow(final ResultSet resultSet, final int i) throws SQLException {
        CarCharacteristics carCharacteristics = new CarCharacteristics(resultSet.getInt(CAR_ID),
                resultSet.getInt(YEAR), resultSet.getInt(MILEAGE), fuelTypeMapper.mapRow(resultSet, i),
                transmissionMapper.mapRow(resultSet, i), carModelMapper.mapRow(resultSet, i));
        LOGGER.debug("row ({}, {}, {}) has been mapped", resultSet.getInt(CAR_ID), resultSet.getInt(YEAR),
                resultSet.getInt(MILEAGE));
        return carCharacteristics;
    }
}
