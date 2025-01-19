package com.example.litsaandroid.service;

import com.example.litsaandroid.model.TokenStorage;
import com.example.litsaandroid.model.User;
import com.example.litsaandroid.model.UserResponse;

import okhttp3.internal.http.StatusLine;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface UserAPIService {
    @GET("user")
    Call<User> getUser(@Header("token") String token);

    @POST("auth/login")
    Call<UserResponse> loginToken (@Body User user);

    @POST("auth/signup")
    Call<StatusLine> create (@Body User user);

    @PATCH("user")
    Call<User> editUser (@Header("token") String name);
}