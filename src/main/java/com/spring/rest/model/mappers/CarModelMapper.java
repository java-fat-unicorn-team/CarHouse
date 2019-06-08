package com.spring.rest.model.mappers;

import com.spring.rest.model.CarModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Car model mapper.
 */
@Component
public class CarModelMapper implements RowMapper<CarModel> {
    /**
     * The constant CAR_MODEL_ID.
     */
    public static final String CAR_MODEL_ID = "car_model_id";
    /**
     * The constant CAR_MODEL.
     */
    public static final String CAR_MODEL = "car_model";
    private CarMakeMapper carMakeMapper;

    /**
     * Instantiates a new Car model mapper.
     *
     * @param pCarMakeMapper the p car make mapper
     */
    @Autowired
    public CarModelMapper(final CarMakeMapper pCarMakeMapper) {
        carMakeMapper = pCarMakeMapper;
    }

    @Override
    public CarModel mapRow(final ResultSet resultSet, final int i)
            throws SQLException {
        return new CarModel(resultSet.getInt(CAR_MODEL_ID),
                carMakeMapper.mapRow(resultSet, i),
                resultSet.getString(CAR_MODEL));
    }
}
