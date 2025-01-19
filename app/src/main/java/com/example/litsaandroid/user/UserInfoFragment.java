package com.example.litsaandroid.user;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.litsaandroid.R;
import com.example.litsaandroid.model.TokenStorage;
import com.example.litsaandroid.model.User;
import com.example.litsaandroid.ui.mainActivity.Splash;

public class UserInfoFragment extends Fragment {

    EditText name;
    EditText email;
    Button updateButton;
    Button outButton;
    Button passwordResetButton;
    Button deleteAccountButton;
    TokenStorage tokenStorage;
    UserViewModel viewModel;

    public UserInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Displaying user name and email
       String token = tokenStorage.getToken();
       name = view.findViewById(R.id.name);
       email = view.findViewById(R.id.email);
       User user = viewModel.getUser(token).getValue();
        assert user != null;
        name.setText(user.getName());
        email.setText(user.getEmail());

        //logic for update button
//        updateButton = view.findViewById(R.id.update);
//        updateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewModel.
//            }
//        });

        //logic for logout button
        outButton = view.findViewById(R.id.logOut);
        outButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tokenStorage.clearToken();
                Intent intent = new Intent(getContext(), LogActivity.class);
                startActivity(intent);
            }
        });

        //logic for delete button
        deleteAccountButton = view.findViewById(R.id.deleteUser);
        deleteAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tokenStorage.clearToken();
                Intent intent = new Intent(getContext(), Splash.class);
                startActivity(intent);
            }
        });

    }
}