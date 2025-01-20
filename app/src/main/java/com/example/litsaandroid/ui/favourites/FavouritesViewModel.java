package com.example.litsaandroid.ui.favourites;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.litsaandroid.model.Favourites;
import com.example.litsaandroid.repositories.UserFavouriteRepository;

import java.util.List;

public class FavouritesViewModel extends AndroidViewModel {

    private UserFavouriteRepository userFavouriteRepository;


    public FavouritesViewModel(@NonNull Application application) {
        super(application);
        this.userFavouriteRepository = new UserFavouriteRepository(application);
    }

    public MutableLiveData<List<Favourites>> getAllFavourites(long userId){
        return userFavouriteRepository.getFavourites(userId);
    }

    public void addFavourites(long userId, Favourites favourites){
        userFavouriteRepository.addFavourite(userId, favourites);
    }

    public void deleteFavourites(long userId, long placeId){
        userFavouriteRepository.deleteFavourite(userId, placeId);
    }
}
