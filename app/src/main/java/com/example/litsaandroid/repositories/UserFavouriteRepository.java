package com.example.litsaandroid.repositories;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.litsaandroid.model.Favourites;
import com.example.litsaandroid.service.UserFavouritePlaceApiService;
import com.example.litsaandroid.service.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserFavouriteRepository {
    private UserFavouritePlaceApiService userFavouritePlaceApiService;
    private MutableLiveData<List<Favourites>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public UserFavouriteRepository(Application application) {
        this.application = application;
        userFavouritePlaceApiService = RetrofitInstance.getService().create(UserFavouritePlaceApiService.class);
    }

    public MutableLiveData<List<Favourites>> getFavourites(long userId){
        Call<List<Favourites>> call = userFavouritePlaceApiService.getAllFavourites(userId);
        call.enqueue(new Callback<List<Favourites>>() {
            @Override
            public void onResponse(Call<List<Favourites>> call, Response<List<Favourites>> response) {
                List<Favourites> responseBody = response.body();
                mutableLiveData.setValue(responseBody);
            }

            @Override
            public void onFailure(Call<List<Favourites>> call, Throwable t) {
                Log.i("GET request failed", t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public void addFavourite(Long userId, Favourites favourites){
        Call<Favourites> call = userFavouritePlaceApiService.addFavourite(userId, favourites);

        call.enqueue(new Callback<Favourites>() {
            @Override
            public void onResponse(Call<Favourites> call, Response<Favourites> response) {
                Toast.makeText(application.getApplicationContext(), "Added to favourites!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Favourites> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(), "Unable to add to favourites.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void deleteFavourite(long userId, long placeId){
        Call<String> call = userFavouritePlaceApiService.deleteFavourite(userId, placeId);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(application.getApplicationContext(), "Removed from favourites", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("DELETE request failed", t.getMessage());

            }
        });
    }

}
