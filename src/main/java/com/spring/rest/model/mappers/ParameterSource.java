package com.spring.rest.model.mappers;

import com.spring.rest.model.CarCharacteristics;
import com.spring.rest.model.CarSale;
import com.spring.rest.model.Comment;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * The class is used to add parameters to SqlParameterSource.
 * SqlParameterSource used by NamedParameterJdbcTemplate for SQL queries
 */
@Component
public class ParameterSource {

    /**
     * Gets car characteristics parameters.
     *
     * @param car the car
     * @return the car characteristics parameters
     */
    public SqlParameterSource getCarCharacteristicsParameters(final CarCharacteristics car) {
        return new MapSqlParameterSource()
                .addValue("id", car.getCarId())
                .addValue("year", car.getYear())
                .addValue("mileage", car.getMileage())
                .addValue("fuelType", car.getFuelType().getFuelTypeId())
                .addValue("transmission", car.getTransmission().getTransmissionId())
                .addValue("carModel", car.getCarModel().getCarModelId());
    }

    /**
     * Gets car sale parameters.
     *
     * @param carSale the car sale
     * @return the car sale parameters
     */
    public SqlParameterSource getCarSaleParameters(final CarSale carSale) {
        return new MapSqlParameterSource()
                .addValue("id", carSale.getCarSaleId())
                .addValue("price", carSale.getPrice())
                .addValue("date", new Date(System.currentTimeMillis()))
                .addValue("userId", carSale.getUser().getUserId())
                .addValue("carId", carSale.getCarCharacteristics().getCarId());
    }

    /**
     * Gets comment parameters.
     *
     * @param comment the comment
     * @return the comment parameters
     */
    public SqlParameterSource getCommentParameters(final Comment comment) {
        return new MapSqlParameterSource()
                .addValue("id", comment.getCommentId())
                .addValue("userName", comment.getUserName())
                .addValue("comment", comment.getComment())
                .addValue("carSaleId", comment.getCarSale().getCarSaleId());
    }
}
