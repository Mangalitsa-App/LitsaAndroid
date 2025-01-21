package com.example.litsaandroid.service;

import com.example.litsaandroid.model.TokenStorage;
import com.example.litsaandroid.model.User;
import com.example.litsaandroid.model.UserRequest;
import com.example.litsaandroid.model.UserResponse;

import okhttp3.internal.http.StatusLine;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserAPIService {
    @GET("user")
    Call<User> getUser(@Header("token") String token);

    @POST("auth/login")
    Call<UserResponse> loginToken(@Body UserRequest user);

    @POST("auth/signup")
    Call<StatusLine> create(@Body User user);

    @PATCH("user/{id}")
    Call<User> editUser(@Path("id") long id, @Body User user);

    @POST("password/request-password-reset")
    Call<StatusLine> resetPassword(@Body String email);
}