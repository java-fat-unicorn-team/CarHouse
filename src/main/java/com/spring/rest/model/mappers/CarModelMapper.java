package com.spring.rest.model.mappers;

import com.spring.rest.model.CarModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CarModelMapper implements RowMapper<CarModel> {
    public static final String CAR_MODEL_ID = "car_model_id";
    public static final String CAR_MODEL = "car_model";
    private CarMakeMapper carMakeMapper;

    @Autowired
    public CarModelMapper(CarMakeMapper pCarMakeMapper) {
        carMakeMapper = pCarMakeMapper;
    }

    @Override
    public CarModel mapRow(ResultSet resultSet, int i) throws SQLException {
        return new CarModel(resultSet.getInt(CAR_MODEL_ID),
                carMakeMapper.mapRow(resultSet, i),
                resultSet.getString(CAR_MODEL));
    }
}
