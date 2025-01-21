package com.example.litsaandroid.model;

public class UserResponse {
    private static String token;
    private static long expiresIn;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        UserResponse.token = token;
    }

    public static long getExpiresIn() {
        return expiresIn;
    }

    public static void setExpiresIn(long expiresIn) {
        UserResponse.expiresIn = expiresIn;
    }

    public UserResponse() {
    }

}
