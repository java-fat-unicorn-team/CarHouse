package com.carhouse.dao.mappers;

import com.carhouse.model.CarSale;
import com.carhouse.model.Comment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class is used to create CarSale from data obtained from database.
 *
 * @author Katuranau Maksimilyan
 * @see CarSale
 * @author Katuranau Maksimilyan
 */
@Component
public class CarSaleMapper implements RowMapper<CarSale> {
    /**
     * The constant CAR_SALE.
     */
    private static final String CAR_SALE_ID = "car_sale_id";
    /**
     * The constant PRICE.
     */
    private static final String PRICE = "price";
    /**
     * The constant DATE.
     */
    private static final String DATE = "date";
    /**
     * The constant IMAGE.
     */
    private static final String IMAGE = "image";
    /**
     * mapper to get User object.
     */
    private UserMapper userMapper;
    /**
     * mapper to get Car object.
     */
    private CarMapper carMapper;
    /**
     * mapper to get list of comments object.
     */
    private CommentMapper commentMapper;
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger(CarSaleMapper.class);

    /**
     * Instantiates a new Car sale mapper.
     *
     * @param userMapper    the user mapper
     * @param carMapper     the car mapper
     * @param commentMapper the comment mapper
     */
    @Autowired
    public CarSaleMapper(final UserMapper userMapper, final CarMapper carMapper, final CommentMapper commentMapper) {
        this.userMapper = userMapper;
        this.carMapper = carMapper;
        this.commentMapper = commentMapper;
    }

    /**
     * Auto increment starts from 1, so if comment id 0 it means what comments do not exist end created by left join.
     * @param resultSet     resultSet
     * @param i             row number
     * @return              carSale object
     * @throws SQLException exception
     */
    @Override
    public CarSale mapRow(final ResultSet resultSet, final int i) throws SQLException {
        CarSale carSale = new CarSale(resultSet.getInt(CAR_SALE_ID), resultSet.getBigDecimal(PRICE),
                resultSet.getDate(DATE), userMapper.mapRow(resultSet, i), carMapper.mapRow(resultSet, i),
                resultSet.getBytes(IMAGE));
        List<Comment> commentList = new ArrayList<>();
        Comment comment;
        do {
            comment = commentMapper.mapRow(resultSet, i);
            if (comment.getCommentId() != 0) {
                commentList.add(comment);
            }
        } while (resultSet.next());
        carSale.setCommentList(commentList);
        LOGGER.debug("row ({}, {}, {}) with {} comments has been mapped", carSale.getCarSaleId(), carSale.getPrice(),
                carSale.getDate(), commentList.size());
        return carSale;
    }
}
