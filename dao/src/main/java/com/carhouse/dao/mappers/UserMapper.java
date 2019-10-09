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

    private static final String USER_ID = "user_id";
    private static final String USER_NAME = "user_name";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
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
