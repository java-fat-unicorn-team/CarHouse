package com.spring.rest.model.mappers;

import com.spring.rest.model.CarSale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CarSaleMapper implements RowMapper<CarSale> {
    public static final String CAR_SALE_ID = "car_sale_id";
    public static final String PRICE = "price";
    public static final String DATE = "date";
    private UserMapper userMapper;
    private CarMapper carMapper;

    @Autowired
    public CarSaleMapper(UserMapper userMapper, CarMapper carMapper) {
        this.userMapper = userMapper;
        this.carMapper = carMapper;
    }

    @Override
    public CarSale mapRow(ResultSet resultSet, int i) throws SQLException {
        return new CarSale(resultSet.getInt(CAR_SALE_ID),
                resultSet.getBigDecimal(PRICE), resultSet.getDate(DATE),
                userMapper.mapRow(resultSet, i), carMapper.mapRow(resultSet, i));
    }
}
