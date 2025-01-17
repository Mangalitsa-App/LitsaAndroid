package com.example.litsaandroid;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.litsaandroid.model.Places;
import com.example.litsaandroid.model.SearchParameters;
import com.example.litsaandroid.ui.mainActivity.MainActivityViewModel;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.material.slider.Slider;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private CheckBox booksCheck;
    private CheckBox nightlifeCheck;
    private CheckBox natureCheck;
    private CheckBox religionCheck;
    private CheckBox foodCheck;
    private CheckBox spaCheck;
    private CheckBox footballCheck;
    private CheckBox allCheck;
    public List<String> keyWord;
    public String address;
    public double radius;
    public Slider slider;
    public double latitude;
    public double longitude;
    Places placesModel = new Places();
    MainActivityViewModel viewModel;


    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        slider = view.findViewById(R.id.continuousSlider);
        slider.addOnSliderTouchListener(touchListener);



        CheckBox artCheck = view.findViewById(R.id.CheckBoxArt);
        booksCheck = view.findViewById(R.id.CheckBoxBooks);
        nightlifeCheck = view.findViewById(R.id.CheckBoxNight);
        natureCheck = view.findViewById(R.id.CheckBoxNature);
        religionCheck = view.findViewById(R.id.CheckBoxReligion);
        foodCheck = view.findViewById(R.id.CheckBoxFood);
        spaCheck = view.findViewById(R.id.CheckBoxSpa);
        footballCheck = view.findViewById(R.id.CheckBoxFootball);
        allCheck = view.findViewById(R.id.CheckBoxALL);

        TableLayout checkTable = view.findViewById(R.id.check_table);

        keyWord = new ArrayList<>();

        artCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                keyWord.add("culture");
                keyWord.add("entertainment");
            } else {
                keyWord.remove("culture");
                keyWord.remove("entertainment");
            }
            Log.i("keywords", keyWord.toString());

        });
        booksCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                keyWord.add("books");
            } else {
                keyWord.remove("books");
            }
            Log.i("keywords", keyWord.toString());
        });
        nightlifeCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                keyWord.add("nightlife");
            } else {
                keyWord.remove("nightlife");
            }
            Log.i("keywords", keyWord.toString());
        });
        natureCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                keyWord.add("nature");
            } else {
                keyWord.remove("nature");
            }
            Log.i("keywords", keyWord.toString());
        });
        religionCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                keyWord.add("religion");
            } else {
                keyWord.remove("religion");
            }
            Log.i("keywords", keyWord.toString());
        });
        foodCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                keyWord.add("food");
            } else {
                keyWord.remove("food");
            }
            Log.i("keywords", keyWord.toString());
        });
        spaCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                keyWord.add("health and wellness");
            } else {
                keyWord.remove("health and wellness");
            }
            Log.i("keywords", keyWord.toString());
        });
        footballCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                keyWord.add("sports");
            } else {
                keyWord.remove("sports");
            }
            Log.i("keywords", keyWord.toString());
        });
        allCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            for (int i = 0; i < checkTable.getChildCount(); i++) {
                View child = checkTable.getChildAt(i);

                if (child instanceof CheckBox) {
                    ((CheckBox) child).setChecked(isChecked);
                } else if (child != null) {
                    TableRow row = (TableRow) child;
                    for (int j = 0; j < row.getChildCount(); j++) {
                        View rowChild = row.getChildAt(j);
                        if (rowChild instanceof CheckBox) {
                            ((CheckBox) rowChild).setChecked(isChecked);
                        }
                    }
                }
            }
            Log.i("keywords", keyWord.toString());
        });
        placesModel.setKeyWord(keyWord);


        AutocompleteSupportFragment autocompleteFragment = FragmentManager.findFragment(view.findViewById(R.id.autocomplete_fragment));
        autocompleteFragment.setPlaceFields(List.of(Place.Field.SHORT_FORMATTED_ADDRESS));

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                // TODO: Get info about the selected place.
                autocompleteFragment.setText(place.getShortFormattedAddress());
                latitude = place.getLocation().latitude;
                longitude = place.getLocation().longitude;
                Log.i("coord", String.valueOf(latitude));
                Log.i("coord", String.valueOf(longitude));
                placesModel.setLatitude(latitude);
                placesModel.setLongitude(longitude);
            }


            @Override
            public void onError(@NonNull Status status) {
                // TODO: Handle the error.
                Log.i("TAG", "An error occurred: " + status);
            }
        });


        Button button = view.findViewById(R.id.submit_button);
        button.setOnClickListener(this);

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);


    }


    @Override
    public void onClick(View v) {
        SearchParameters params = new SearchParameters(
                placesModel.getLatitude(),
                placesModel.getLongitude(),
                placesModel.getRadius(),
                placesModel.getKeyWord()
        );
        PlacesFragment placesFragment = new PlacesFragment();
        Bundle args = new Bundle();
        args.putParcelable("search_parameters", params);
        placesFragment.setArguments(args);

        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.main, placesFragment).addToBackStack(null).commit();
    }
    final Slider.OnSliderTouchListener touchListener =
            new Slider.OnSliderTouchListener() {
                @Override
                public void onStartTrackingTouch(Slider slider) {
                }

                @Override
                public void onStopTrackingTouch(Slider slider) {
                    radius = slider.getValue();
                    placesModel.setRadius(radius);
                    Log.i("radius", String.valueOf(radius));
                }
            };
}