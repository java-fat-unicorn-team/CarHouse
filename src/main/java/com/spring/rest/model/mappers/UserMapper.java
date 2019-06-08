package com.spring.rest.model.mappers;

import com.spring.rest.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserMapper implements RowMapper<User> {
    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";


    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        return new User(resultSet.getInt(USER_ID), resultSet.getString(USER_NAME),
                resultSet.getString(PHONE_NUMBER), resultSet.getString(LOGIN),
                resultSet.getString(PASSWORD));
    }
}
