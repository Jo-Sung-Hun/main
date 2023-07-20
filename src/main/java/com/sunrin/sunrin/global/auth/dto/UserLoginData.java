package com.sunrin.sunrin.global.auth.dto;

import lombok.Builder;

public class UserLoginData {
    private String userLoginUsername;
    private String userLoginPassword;
    @Builder
    public UserLoginData(String userLoginEmail, String userLoginPassword) {
        this.userLoginUsername = userLoginEmail;
        this.userLoginPassword = userLoginPassword;
    }
    public UserLoginData() {
    }
    public String getUserLoginEmail() {
        return userLoginUsername;
    }
    public void setUserLoginEmail(String userLoginEmail) {
        this.userLoginUsername = userLoginEmail;
    }
    public String getUserLoginPassword() {
        return userLoginPassword;
    }
    public void setUserLoginPassword(String userLoginPassword) {
        this.userLoginPassword = userLoginPassword;
    }

    @Override
    public String toString() {

        return "UserLoginData{" +
                "userLoginEmail='" + userLoginUsername + '\'' +
                ", userLoginPassword='" + userLoginPassword + '\'' +
                '}';
    }
}
