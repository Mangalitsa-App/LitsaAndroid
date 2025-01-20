package com.example.litsaandroid.service;

import com.example.litsaandroid.model.Favourites;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserFavouritePlaceApiService {

    @GET("favourites/{user_id}")
    Call<List<Favourites>> getAllFavourites(@Path("user_id") long userId);

    @POST("favourites/{user_id}")
    Call<Favourites> addFavourite(@Path("user_id")long userId, @Body Favourites favourites);

    @DELETE("favourites/{user_id}/{place_id}")
    Call<String> deleteFavourite(@Path("user_id") long userId, @Path("place_id") long placeId);
}
