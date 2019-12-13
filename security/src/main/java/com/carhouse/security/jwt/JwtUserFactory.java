package com.carhouse.security.jwt;

import com.carhouse.model.User;

/**
 * The type Jwt user factory.
 */
public final class JwtUserFactory {

    /**
     * Create jwt user.
     *
     * @param user the user
     * @return the jwt user
     */
    public static JwtUser create(final User user) {
        return new JwtUser(
                user.getUserId(),
                user.getUserName(),
                user.getPhoneNumber(),
                user.getLogin(),
                user.getPassword()
        );
    }
}
