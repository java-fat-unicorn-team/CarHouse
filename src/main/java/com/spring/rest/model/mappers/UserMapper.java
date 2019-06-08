package com.spring.rest.model.mappers;

import com.spring.rest.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type User mapper.
 */
@Component
public class UserMapper implements RowMapper<User> {
    /**
     * The constant USER_ID.
     */
    public static final String USER_ID = "user_id";
    /**
     * The constant USER_NAME.
     */
    public static final String USER_NAME = "user_name";
    /**
     * The constant PHONE_NUMBER.
     */
    public static final String PHONE_NUMBER = "phone_number";
    /**
     * The constant LOGIN.
     */
    public static final String LOGIN = "login";
    /**
     * The constant PASSWORD.
     */
    public static final String PASSWORD = "password";


    @Override
    public User mapRow(final ResultSet resultSet, final int i)
            throws SQLException {
        return new User(resultSet.getInt(USER_ID),
                resultSet.getString(USER_NAME),
                resultSet.getString(PHONE_NUMBER),
                resultSet.getString(LOGIN),
                resultSet.getString(PASSWORD));
    }
}
