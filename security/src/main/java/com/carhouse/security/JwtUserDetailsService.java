package com.carhouse.security;

import com.carhouse.model.User;
import com.carhouse.security.jwt.JwtUserFactory;
import com.carhouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * The type Jwt user details service.
 */
@Service(value = "customUserDetailsService")
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    /**
     * Instantiates a new Jwt user details service.
     *
     * @param userService the user service
     */
    @Autowired
    public JwtUserDetailsService(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException {
        User user = userService.findByUserLogin(userName);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid login or password");
        }

        return JwtUserFactory.create(user);
    }
}
