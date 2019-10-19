package com.carhouse.dao.mappers;

import com.carhouse.model.dto.CarSaleDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Used to create CarSaleDto from data obtained from database.
 *
 * @author Katuranau Maksimilyan
 * @see CarSaleDto
 */
@Component
public class CarSaleDtoMapper implements RowMapper<CarSaleDto> {

    private static final String CAR_SALE_ID = "car_sale_id";
    private static final String PRICE = "price";
    private static final String DATE = "date";
    private static final String YEAR = "year";
    private static final String MILEAGE = "mileage";
    private static final String FUEL_TYPE = "fuel_type";
    private static final String TRANSMISSION = "transmission";
    private static final String CAR_MAKE = "car_make";
    private static final String CAR_MODEL = "car_model";
    private static final String IMAGE_NAME = "image_name";

    private static final Logger LOGGER = LogManager.getLogger(CarSaleDtoMapper.class);

    @Override
    public CarSaleDto mapRow(final ResultSet resultSet, final int i) throws SQLException {
        CarSaleDto carSaleDto = new CarSaleDto()
                .setCarSaleId(resultSet.getInt(CAR_SALE_ID))
                .setPrice(resultSet.getBigDecimal(PRICE))
                .setDate(resultSet.getDate(DATE))
                .setYear(resultSet.getDate(YEAR))
                .setMileage(resultSet.getInt(MILEAGE))
                .setFuelType(resultSet.getString(FUEL_TYPE))
                .setTransmission(resultSet.getString(TRANSMISSION))
                .setCarMake(resultSet.getString(CAR_MAKE))
                .setCarModel(resultSet.getString(CAR_MODEL))
                .setImageName(resultSet.getString(IMAGE_NAME));
        LOGGER.debug("object carSaleDto has been mapped: {}", carSaleDto);
        return carSaleDto;
    }
}
