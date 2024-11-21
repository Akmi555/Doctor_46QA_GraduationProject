package com.doctor.pages;

public class UserPage {
    private String email;
    private String password;

    public UserPage setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserPage setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
