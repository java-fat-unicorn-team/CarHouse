package com.carhouse.service;

import com.carhouse.model.User;
import javassist.NotFoundException;

/**
 * The interface User service.
 */
public interface UserService {
    /**
     * Find by user login user.
     *
     * @param userLogin the name
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
     * Delete user.
     *
     * @param userId the user id
     * @throws NotFoundException the not found exception
     */
    void deleteUser(int userId) throws NotFoundException;
}
