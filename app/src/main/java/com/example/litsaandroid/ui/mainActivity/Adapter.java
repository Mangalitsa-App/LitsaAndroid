package com.example.litsaandroid.ui.mainActivity;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.litsaandroid.R;
import com.example.litsaandroid.databinding.PlacesItemBinding;
import com.example.litsaandroid.model.Favourites;
import com.example.litsaandroid.model.Places;


import com.example.litsaandroid.model.User;
import com.example.litsaandroid.ui.favourites.FavouritesFragment;
import com.example.litsaandroid.ui.favourites.FavouritesViewModel;
import com.example.litsaandroid.user.UserViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.PlacesItemViewHolder> {
    private List<Places> placesList;
    private Context context;
    private Favourites favourites;
    private FavouritesViewModel favouritesViewModel;
    private FavouritesFragment favouritesFragment;
    private final RecyclerViewInterface recyclerViewInterface;
    private UserViewModel user;

    public Adapter(List<Places> placesList, RecyclerViewInterface recyclerViewInterface) {
        this.placesList = placesList;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    public static class PlacesItemViewHolder extends RecyclerView.ViewHolder{
        private PlacesItemBinding binding;

        public PlacesItemViewHolder(PlacesItemBinding placesItemBinding,
                                RecyclerViewInterface recyclerViewInterface){
            super(placesItemBinding.getRoot());
            this.binding = placesItemBinding;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface!= null){
                        int position = getAdapterPosition();
                        if (position!= RecyclerView.NO_POSITION)
                            recyclerViewInterface.onItemClick(position);
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public PlacesItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PlacesItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.places_item,
                parent,
                false);
        return new PlacesItemViewHolder(binding, recyclerViewInterface);}

    @Override
    public void onBindViewHolder(@NonNull PlacesItemViewHolder holder, int position) {
    Places place = placesList.get(position);
    holder.binding.setPlaces(place);

//        Glide.with(holder.itemView.getContext())
//                .load(place.getImg())
//                .placeholder(R.drawable.place_image)
//                .fitCenter()
//                .into(holder.binding.placesImage);

        if(place.getPriceLevel() != null) {
            switch (place.getPriceLevel()) {
                case "PRICE_LEVEL_INEXPENSIVE":
                    holder.binding.price.setImageResource(R.drawable.one_pound);
                    break;
                case "PRICE_LEVEL_MODERATE":
                    holder.binding.price.setImageResource(R.drawable.two_pound);
                    break;
                case "PRICE_LEVEL_EXPENSIVE":
                    holder.binding.price.setImageResource(R.drawable.three_pound);
                    break;
                case "PRICE_LEVEL_VERY_EXPENSIVE":
                    holder.binding.price.setImageResource(R.drawable.four_pound);
                    break;
                default:
                    holder.binding.price.setImageResource(R.drawable.ic_price_foreground);
            }
        }


    FloatingActionButton favouritesButton = holder.binding.floatingActionButtonFavourites;
    favouritesButton.setSelected(true);
    favouritesButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Favourites newFavourite = new Favourites();

            newFavourite.setDisplayName(place.getDisplayName());
//            newFavourite.setPhotoLink(place.getImg());
//            newFavourite.setFormattedAddress(place.getFormattedAddress());
//            newFavourite.setWebsite(place.getWebsiteUri());
//            newFavourite.setPriceLevel(place.getPriceLevel());
//            newFavourite.setTypes(place.getTypes().toString());

            try {
                User loggedInUser = user.getUser();
                long loggedInUserId = loggedInUser.getId();
                favouritesViewModel.addFavourites(loggedInUserId, newFavourite);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            Bundle args = new Bundle();
            args.putParcelable("favourites_body", newFavourite);
            favouritesFragment.setArguments(args);

            if(favouritesButton.isSelected()){
                favouritesButton.setImageResource(R.drawable.fav_click_foreground);
                favouritesButton.setSelected(false);
            }
            else{
                favouritesButton.setImageResource(R.drawable.fav_unclick_foreground);
                favouritesButton.setSelected(true);
            }
        }
    });
    }

    @Override
    public int getItemCount() {
        return placesList.size();
    }

    public void setPlacesList(List<Places> placesList) {
        this.placesList = placesList;
    }
}
