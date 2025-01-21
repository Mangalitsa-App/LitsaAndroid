package com.example.litsaandroid.ui.favourites;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.litsaandroid.R;
import com.example.litsaandroid.databinding.FavouritesItemBinding;
import com.example.litsaandroid.model.Favourites;
import com.example.litsaandroid.model.Places;
import com.example.litsaandroid.ui.mainActivity.Adapter;

import java.util.List;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.FavouritesItemViewHolder> {

    private List<Favourites> favouritesList;
    private Context context;

    public FavouritesAdapter(List<Favourites> favouritesList, Context context) {
        this.favouritesList = favouritesList;
        this.context = context;
    }

    @NonNull
    @Override
    public FavouritesItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FavouritesItemBinding favouritesItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.favourites_item,
                parent,
                false
        );
        return new FavouritesItemViewHolder(favouritesItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouritesItemViewHolder holder, int position) {
        Favourites favourite = favouritesList.get(position);
        holder.binding.setFavourites(favourite);
    }

    @Override
    public int getItemCount() {
        return favouritesList.size();
    }

    public void setFavouritesList(List<Favourites> favouritesList) {
        this.favouritesList = favouritesList;
    }

    public static class FavouritesItemViewHolder extends RecyclerView.ViewHolder {
        private FavouritesItemBinding binding;

        public FavouritesItemViewHolder(FavouritesItemBinding favouritesItemBinding) {
            super(favouritesItemBinding.getRoot());
            this.binding = favouritesItemBinding;
        }
    }
}
