package com.carhouse.dao.mappers;

import com.carhouse.dao.exception.IncorrectJsonException;
import com.carhouse.model.dto.CarCharacteristicsDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The Class is used to create CarCharacteristicsDto from data obtained from database.
 * Gets data as json which is generated in sql query
 *
 * @author Katuranau Maksimilyan
 * @see CarCharacteristicsDto
 */
@Component
public class CarCharacteristicsDtoMapper implements RowMapper<CarCharacteristicsDto> {

    private static final String CAR_CHARACTERISTICS = "car_characteristics";

    private static final Logger LOGGER = LogManager.getLogger(CarCharacteristicsDtoMapper.class);

    private final ObjectMapper objectMapper;

    /**
     * Instantiates a new Car characteristics dto mapper.
     *
     * @param objectMapper the object mapper
     */
    @Autowired
    public CarCharacteristicsDtoMapper(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public CarCharacteristicsDto mapRow(final ResultSet resultSet, final int i) throws SQLException {
        try {
            LOGGER.debug("mapping CarCharacteristicsDto object");
            return objectMapper.readValue(resultSet.getString(CAR_CHARACTERISTICS), CarCharacteristicsDto.class);
        } catch (JsonProcessingException e) {
            LOGGER.error("CarCharacteristicsDto failed, incorrect json obtained");
            throw new IncorrectJsonException("Sorry, Incorrect JSON obtained from the database, we are working on it");
        }
    }
}
