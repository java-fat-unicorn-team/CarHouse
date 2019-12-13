package com.carhouse.security.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * The type Jwt user.
 */
public class JwtUser implements UserDetails {

    private final int userId;
    private final String userName;
    private final String phoneNumber;
    private final String login;
    private final String password;

    /**
     * Instantiates a new Jwt user.
     *
     * @param userId      the user id
     * @param userName    the user name
     * @param phoneNumber the phone number
     * @param login       the login
     * @param password    the password
     */
    public JwtUser(final int userId, final String userName, final String phoneNumber, final String login,
                   final String password) {
        this.userId = userId;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.login = login;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Gets real user name.
     *
     * @return the real user name
     */
    public String getRealUserName() {
        return userName;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }
}
