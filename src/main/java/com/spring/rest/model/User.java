package com.spring.rest.model;

public class User {
    private int userId;
    private String userName;
    private String phoneNumber;
    private String login;
    private String password;

    public User(final String pUserName) {
        userName = pUserName;
    }

    public User(int userId, String userName, String phoneNumber, String login, String password) {
        this.userId = userId;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.login = login;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String pUserName) {
        this.userName = pUserName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String pPhoneNumber) {
        this.phoneNumber = pPhoneNumber;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String pLogin) {
        this.login = pLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String pPassword) {
        this.password = pPassword;
    }

    @Override
    public String toString() {
        return "userId=" + userId +
                ", userName='" + userName + '\'';
    }
}
