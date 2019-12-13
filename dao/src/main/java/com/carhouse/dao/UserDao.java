package com.carhouse.dao;

import com.carhouse.model.User;

/**
 * The interface User dao.
 */
public interface UserDao {
    /**
     * Find by user login user.
     *
     * @param userLogin the user login
     * @return the user
     */
    User findByUserLogin(String userLogin);

    /**
     * Add user user.
     *
     * @param user the user
     * @return the user
     */
    User addUser(User user);

    /**
     * Delete user boolean.
     *
     * @param userId the user id
     * @return the boolean
     */
    boolean deleteUser(int userId);
}
