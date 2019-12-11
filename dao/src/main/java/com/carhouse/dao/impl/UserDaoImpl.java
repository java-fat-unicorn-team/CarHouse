package com.carhouse.dao.impl;

import com.carhouse.dao.UserDao;
import com.carhouse.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl implements UserDao {
    @Override
    public User findByUsername(String name) {
        return new User(1, "maksim", "+375336648714", "maksim",
                "$2a$04$b3NJxSliunAizUjZfdt0RObfbP3CnWqMQqM4QwPlIDi7R/iImkcxu");
    }

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public User getById(int userId) {
        return null;
    }

    @Override
    public void deleteUser(int userId) {

    }
}
