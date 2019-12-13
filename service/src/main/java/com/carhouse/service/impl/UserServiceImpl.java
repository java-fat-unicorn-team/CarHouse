package com.carhouse.service.impl;

import com.carhouse.dao.UserDao;
import com.carhouse.model.User;
import com.carhouse.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * The class provides method to manage User models on service layer.
 * It is realisation of UserService interface
 *
 * @author Katuranau Maksimilyan
 * @see UserService
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * Instantiates a new User service.
     *
     * @param userDao the class is provided CRUD operations for user model.
     * @param passwordEncoder the class is used to code password.
     */
    @Autowired
    public UserServiceImpl(final UserDao userDao, final BCryptPasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Find by user login user.
     *
     * @param userLogin the name
     * @return the user
     */
    @Override
    public User findByUserLogin(final String userLogin) {
        try {
            return userDao.findByUserLogin(userLogin);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    /**
     * Add user user.
     *
     * @param user the user
     * @return the user
     */
    @Override
    public User addUser(final User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.addUser(user);
    }

    /**
     * Delete user.
     *
     * @param userId the user id
     * @throws NotFoundException the not found exception
     */
    @Override
    public void deleteUser(final int userId) throws NotFoundException {
        try {
            if (!userDao.deleteUser(userId)) {
                throw new NotFoundException("Something went wrong, user with id = " + userId + " was not deleted");
            }
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException("there is not user with id = " + userId + " to delete");
        }
    }
}
