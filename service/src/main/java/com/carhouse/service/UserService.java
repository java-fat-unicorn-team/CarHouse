package com.carhouse.service;

import com.carhouse.model.User;

public interface UserService {
    User findByUsername(String name);

    User addUser(User user);

    User getById(int userId);

    void deleteUser(int userId);
}
