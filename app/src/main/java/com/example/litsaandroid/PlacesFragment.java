package com.example.litsaandroid;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.litsaandroid.databinding.FragmentPlacesBinding;
import com.example.litsaandroid.model.Places;
import com.example.litsaandroid.ui.mainActivity.Adapter;
import com.example.litsaandroid.ui.mainActivity.MainActivityViewModel;
import com.example.litsaandroid.ui.mainActivity.RecyclerViewInterface;

import java.util.ArrayList;
import java.util.List;

public class PlacesFragment extends Fragment implements RecyclerViewInterface {

    private RecyclerView recyclerView;
    private ArrayList<Places> placesList;
    private Adapter adapter;
    private FragmentPlacesBinding binding;
    private MainActivityViewModel mainActivityViewModel;

    private static final String PLACES_KEY = "places";



    public PlacesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_places, container, false);
    return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        displayInRecyclerView();
        getAllPlaces();
    }

    private void getAllPlaces(){
    mainActivityViewModel.getAllPlaces().observe(getViewLifecycleOwner(), new Observer<List<Places>>() {
        @Override
        public void onChanged(List<Places> places) {
            placesList = (ArrayList<Places>) places;
            displayInRecyclerView();
        }
    });
    }
    private void displayInRecyclerView(){
       recyclerView = binding.recyclerview;
       adapter = new Adapter(placesList, this.getContext(), this);
       LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
       recyclerView.setAdapter(adapter);
       recyclerView.setLayoutManager(layoutManager);
       adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {
    Intent intent = new Intent(this.getContext(), PlaceClickActivity.class);
    intent.putExtra(PLACES_KEY, placesList.get(position));
    startActivity(intent);
    }
}