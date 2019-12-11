package com.carhouse.dao;

import com.carhouse.model.User;

public interface UserDao {
    User findByUsername(String name);

    User addUser(User user);

    User getById(int userId);

    void deleteUser(int userId);
}
