package com.example.litsaandroid.ui.mainActivity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.util.Log;

import android.view.MenuItem;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.litsaandroid.ui.favourites.FavouritesFragment;
import com.example.litsaandroid.ui.mainActivity.HomeFragment;
import com.example.litsaandroid.R;
import com.example.litsaandroid.user.UserInfoFragment;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    private NavigationBarView bottomNavigationView;
    private HomeFragment homeFragment = new HomeFragment();
    private FavouritesFragment favouritesFragment = new FavouritesFragment();
    private UserInfoFragment userInfoFragment = new UserInfoFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

//extracting Secret from metadata
        ApplicationInfo ai = null;
        try {
            ai = getApplicationContext().getPackageManager()
                    .getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
        String key = ai.metaData.getString("KEY");
        assert key != null;
        Log.i("TAG", key);
        //initialise Places
        com.google.android.libraries.places.api.Places.initializeWithNewPlacesApiEnabled(getApplicationContext(),key);
        PlacesClient placesClient = Places.createClient(this);


        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.home) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, homeFragment)
                    .commit();
            return true;
        }
        if (item.getItemId() == R.id.favourites) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, favouritesFragment)
                    .commit();
            return true;
        }
        if (item.getItemId() == R.id.user) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, userInfoFragment)
                    .commit();
            return true;
        }
        return false;
    }
}