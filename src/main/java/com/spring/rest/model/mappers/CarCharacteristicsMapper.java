package com.spring.rest.model.mappers;

import com.spring.rest.model.CarCharacteristics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The is used to create CarCharacteristics from data returned from database.
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
     * Instantiates a new CarCharacteristics mapper.
     *
     * @param fuelTypeMapper     the fuel type mapper
     * @param transmissionMapper the transmission mapper
     * @param carModelMapper     the car model mapper
     */
    @Autowired
    public CarCharacteristicsMapper(final FuelTypeMapper fuelTypeMapper,
                                    final TransmissionMapper transmissionMapper,
                                    final CarModelMapper carModelMapper) {
        this.fuelTypeMapper = fuelTypeMapper;
        this.transmissionMapper = transmissionMapper;
        this.carModelMapper = carModelMapper;
    }


    @Override
    public CarCharacteristics mapRow(final ResultSet resultSet, final int i)
            throws SQLException {
        return new CarCharacteristics(resultSet.getInt(CAR_ID),
                resultSet.getDate(YEAR), resultSet.getInt(MILEAGE),
                fuelTypeMapper.mapRow(resultSet, i),
                transmissionMapper.mapRow(resultSet, i),
                carModelMapper.mapRow(resultSet, i));
    }
}
