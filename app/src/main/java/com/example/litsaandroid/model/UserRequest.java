package com.example.litsaandroid.model;

public class UserRequest {
    String email;
    String password;

    public UserRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
