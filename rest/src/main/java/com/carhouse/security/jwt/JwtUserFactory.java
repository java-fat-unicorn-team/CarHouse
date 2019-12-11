package com.carhouse.security.jwt;

import com.carhouse.model.User;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getUserId(),
                user.getUserName(),
                user.getPhoneNumber(),
                user.getLogin(),
                user.getPassword()
        );
    }
}
