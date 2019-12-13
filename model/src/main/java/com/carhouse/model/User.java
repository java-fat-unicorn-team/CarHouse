package com.carhouse.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

/**
 * The User model.
 * The model includes user name, user's phone number, login and password.
 * Each user can have more then one car sale announcements.
 * @author Katuranau Maksimilyan
 */
public class User {
    @PositiveOrZero(message = "user id can't be negative")
    private int userId;
    @NotBlank
    private String userName;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String login;
    @NotBlank
    private String password;

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param userId the user id
     */
    public User(final int userId) {
        this.userId = userId;
    }

    /**
     * Instantiates a new User.
     *
     * @param userId      the user id
     * @param userName    the user name
     * @param phoneNumber the phone number
     * @param login       the login
     * @param password    the password
     */
    public User(final int userId, final String userName, final String phoneNumber, final String login,
                final String password) {
        this.userId = userId;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.login = login;
        this.password = password;
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
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(final int userId) {
        this.userId = userId;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(final String userName) {
        this.userName = userName;
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
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets login.
     *
     * @param login the login
     */
    public void setLogin(final String login) {
        this.login = login;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    @Override
    public final String toString() {
        return "userId=" + userId
                + ", userName='" + userName + '\'';
    }
}
