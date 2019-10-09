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

    private static final String CAR_ID = "car_id";
    private static final String YEAR = "year";
    private static final String MILEAGE = "mileage";
    private FuelTypeMapper fuelTypeMapper;
    private TransmissionMapper transmissionMapper;
    private CarModelMapper carModelMapper;
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
        Car car = new Car(resultSet.getInt(CAR_ID))
                .setYear(resultSet.getDate(YEAR))
                .setMileage(resultSet.getInt(MILEAGE))
                .setFuelType(fuelTypeMapper.mapRow(resultSet, i))
                .setTransmission(transmissionMapper.mapRow(resultSet, i))
                .setCarModel(carModelMapper.mapRow(resultSet, i));
        LOGGER.debug("row ({}, {}, {}) has been mapped", car.getCarId(), car.getYear(), car.getMileage());
        return car;
    }
}
