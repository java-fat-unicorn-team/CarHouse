package com.carhouse.dao.mappers;

import com.carhouse.model.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Used to create Car from data obtained from database.
 *
 * @author Katuranau Maksimilyan
 * @see Car
 */
@Component
public class CarMapper implements RowMapper<Car> {
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
    private static final Logger LOGGER = LogManager.getLogger(CarMapper.class);

    /**
     * Instantiates a new Car mapper.
     *
     * @param fuelTypeMapper     the fuel type mapper
     * @param transmissionMapper the transmission mapper
     * @param carModelMapper     the car model mapper
     */
    @Autowired
    public CarMapper(final FuelTypeMapper fuelTypeMapper, final TransmissionMapper transmissionMapper,
                     final CarModelMapper carModelMapper) {
        this.fuelTypeMapper = fuelTypeMapper;
        this.transmissionMapper = transmissionMapper;
        this.carModelMapper = carModelMapper;
    }


    @Override
    public Car mapRow(final ResultSet resultSet, final int i) throws SQLException {
        Car car = new Car(resultSet.getInt(CAR_ID),
                resultSet.getDate(YEAR), resultSet.getInt(MILEAGE), fuelTypeMapper.mapRow(resultSet, i),
                transmissionMapper.mapRow(resultSet, i), carModelMapper.mapRow(resultSet, i), null);
        LOGGER.debug("row ({}, {}, {}) has been mapped", resultSet.getInt(CAR_ID), resultSet.getDate(YEAR),
                resultSet.getInt(MILEAGE));
        return car;
    }
}
