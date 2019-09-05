package com.carhouse.dao.mappers;

import com.carhouse.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The Class is used to create User from data obtained from database.
 * @see User
 * @author Katuranau Maksimilyan
 */
@Component
public class UserMapper implements RowMapper<User> {
    /**
     * The constant USER_ID.
     */
    private static final String USER_ID = "user_id";
    /**
     * The constant USER_NAME.
     */
    private static final String USER_NAME = "user_name";
    /**
     * The constant PHONE_NUMBER.
     */
    private static final String PHONE_NUMBER = "phone_number";
    /**
     * The constant LOGIN.
     */
    private static final String LOGIN = "login";
    /**
     * The constant PASSWORD.
     */
    private static final String PASSWORD = "password";

    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger(UserMapper.class);

    @Override
    public User mapRow(final ResultSet resultSet, final int i) throws SQLException {
        User user = new User(resultSet.getInt(USER_ID), resultSet.getString(USER_NAME),
                resultSet.getString(PHONE_NUMBER), resultSet.getString(LOGIN), resultSet.getString(PASSWORD));
        LOGGER.debug("row ({},{},{},{},{}) has been mapped", user.getUserId(), user.getUserName(),
                user.getPhoneNumber(), user.getLogin(), user.getPassword());
        return user;
    }
}
