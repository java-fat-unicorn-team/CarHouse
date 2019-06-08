package com.spring.rest.model.mappers;

import com.spring.rest.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CarMapper implements RowMapper<Car> {
    public static final String CAR_ID = "car_id";
    public static final String YEAR = "year";
    public static final String MILEAGE = "mileage";
    private FuelTypeMapper fuelTypeMapper;
    private TransmissionMapper transmissionMapper;
    private CarModelMapper carModelMapper;

    @Autowired
    public CarMapper(FuelTypeMapper fuelTypeMapper,
                     TransmissionMapper transmissionMapper, CarModelMapper carModelMapper) {
        this.fuelTypeMapper = fuelTypeMapper;
        this.transmissionMapper = transmissionMapper;
        this.carModelMapper = carModelMapper;
    }


    @Override
    public Car mapRow(final ResultSet resultSet, final int i)
            throws SQLException {
        return new Car(resultSet.getInt(CAR_ID), resultSet.getDate(YEAR),
                resultSet.getInt(MILEAGE), fuelTypeMapper.mapRow(resultSet, i),
                transmissionMapper.mapRow(resultSet, i), carModelMapper.mapRow(resultSet, i));
    }
}
