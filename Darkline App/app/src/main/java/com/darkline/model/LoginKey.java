package com.darkline.model;

public class LoginKey {
    public String jwtToken;
    public String password;

    public LoginKey() {
    }

    public LoginKey(String jwtToken, String password) {
        this.jwtToken = jwtToken;
        this.password = password;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginKey{" +
                "jwtToken='" + jwtToken + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
