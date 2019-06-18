package com.spring.rest.model.mappers;

import com.spring.rest.model.Comment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The Class is used to create Comment from data obtained from database.
 * @author Katuranau Maksimilyan
 */
@Component
public class CommentMapper implements RowMapper<Comment> {
    /**
     * The constant COMMENT_ID.
     */
    public static final String COMMENT_ID = "comment_id";
    /**
     * The constant USER_NAME.
     */
    public static final String USER_NAME = "user_name";
    /**
     * The constant COMMENT.
     */
    public static final String COMMENT = "comment";
    /**
     * mapper to get CarSale object.
     */
    private CarSaleMapper carSaleMapper;
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger(CommentMapper.class);

    /**
     * Instantiates a new Comment mapper.
     *
     * @param carSaleMapper the car sale mapper
     */
    @Autowired
    public CommentMapper(final CarSaleMapper carSaleMapper) {
        this.carSaleMapper = carSaleMapper;
    }

    @Override
    public Comment mapRow(final ResultSet resultSet, final int i) throws SQLException {
        Comment comment = new Comment(resultSet.getInt(COMMENT_ID), resultSet.getString(USER_NAME),
                resultSet.getString(COMMENT), carSaleMapper.mapRow(resultSet, i));
        LOGGER.debug("row ({}, {}, {}) has been mapped", resultSet.getInt(COMMENT_ID), resultSet.getString(USER_NAME),
                resultSet.getString(COMMENT));
        return comment;
    }
}
