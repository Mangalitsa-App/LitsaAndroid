package com.example.litsaandroid.ui.favourites;

import android.content.Context;
import android.view.View;

import com.example.litsaandroid.model.Favourites;
import com.example.litsaandroid.model.User;

public class FavouritesClickHandler {

    private Context context;
    private Favourites userFavouritePlace;
    private FavouritesViewModel favouritesViewModel;
    private User user;

    public FavouritesClickHandler(Context context, Favourites userFavouritePlace, FavouritesViewModel favouritesViewModel) {
        this.context = context;
        this.userFavouritePlace = userFavouritePlace;
        this.favouritesViewModel = favouritesViewModel;
    }

    public void onFavouriteButtonClicked(View view){
        favouritesViewModel.deleteFavourites(USERID, FAVOURITESID);
    }

    }

