package com.spring.rest.model.mappers;

import com.spring.rest.model.CarSale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Car sale mapper.
 */
@Component
public class CarSaleMapper implements RowMapper<CarSale> {
    /**
     * The constant CAR_SALE_ID.
     */
    public static final String CAR_SALE_ID = "car_sale_id";
    /**
     * The constant PRICE.
     */
    public static final String PRICE = "price";
    /**
     * The constant DATE.
     */
    public static final String DATE = "date";
    private UserMapper userMapper;
    private CarMapper carMapper;

    /**
     * Instantiates a new Car sale mapper.
     *
     * @param userMapper the user mapper
     * @param carMapper  the car mapper
     */
    @Autowired
    public CarSaleMapper(final UserMapper userMapper,
                         final CarMapper carMapper) {
        this.userMapper = userMapper;
        this.carMapper = carMapper;
    }

    @Override
    public CarSale mapRow(final ResultSet resultSet, final int i)
            throws SQLException {
        return new CarSale(resultSet.getInt(CAR_SALE_ID),
                resultSet.getBigDecimal(PRICE), resultSet.getDate(DATE),
                userMapper.mapRow(resultSet, i),
                carMapper.mapRow(resultSet, i));
    }
}
