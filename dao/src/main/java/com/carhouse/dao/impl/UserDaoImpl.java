package com.carhouse.dao.impl;

import com.carhouse.dao.UserDao;
import com.carhouse.dao.mappers.ParameterSource;
import com.carhouse.dao.mappers.UserMapper;
import com.carhouse.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

/**
 * The type User dao.
 */
@Component
public class UserDaoImpl implements UserDao {

    @Value("${user.by.user.login.get}")
    private String GET_USER_BY_USER_LOGIN_SQL;

    @Value("${user.add}")
    private String ADD_USER_SQL;

    @Value("${user.delete}")
    private String DELETE_USER_SQL;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final UserMapper userMapper;
    private final ParameterSource parameterSource;


    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

    /**
     * Instantiates a new User dao.
     *
     * @param namedParameterJdbcTemplate the named parameter jdbc template
     * @param userMapper                 the user mapper
     * @param parameterSource            the parameter source
     */
    @Autowired
    public UserDaoImpl(final NamedParameterJdbcTemplate namedParameterJdbcTemplate, final UserMapper userMapper,
                       final ParameterSource parameterSource) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.userMapper = userMapper;
        this.parameterSource = parameterSource;
    }

    /**
     * Find by user login user.
     *
     * @param userLogin the user login
     * @return the user
     */
    @Override
    public User findByUserLogin(final String userLogin) {
        LOGGER.debug("method findByUserLogin with login: {}", userLogin);
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userLogin", userLogin);
        return namedParameterJdbcTemplate.queryForObject(GET_USER_BY_USER_LOGIN_SQL, parameters, userMapper);
    }

    /**
     * Add user user.
     *
     * @param user the user
     * @return the user
     */
    @Override
    public User addUser(final User user) {
        LOGGER.debug("method addUser with parameter: [{}]", user);
        MapSqlParameterSource parameters = parameterSource.getUserParameters(user);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(ADD_USER_SQL, parameters, keyHolder);
        user.setUserId(keyHolder.getKey().intValue());
        return user;
    }

    /**
     * Delete user boolean.
     *
     * @param userId the user id
     * @return the boolean
     */
    @Override
    public boolean deleteUser(final int userId) {
        LOGGER.debug("method deleteUser with parameter: [{}]", userId);
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userId", userId);
        return namedParameterJdbcTemplate.update(DELETE_USER_SQL, parameters) == 1;
    }
}
