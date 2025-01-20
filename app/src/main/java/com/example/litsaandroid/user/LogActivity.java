package com.example.litsaandroid.user;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.litsaandroid.R;
import com.example.litsaandroid.model.User;
import com.example.litsaandroid.model.UserRequest;
import com.example.litsaandroid.model.UserResponse;
import com.example.litsaandroid.ui.mainActivity.HomeFragment;
import com.example.litsaandroid.ui.mainActivity.MainActivity;
import com.example.litsaandroid.ui.mainActivity.Splash;

import java.util.List;
import java.util.Objects;

import retrofit2.Callback;

public class LogActivity extends AppCompatActivity {

    private UserViewModel viewModel;

    public UserRequest user = new UserRequest();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.log_in);


        EditText emailbox = findViewById(R.id.email);
        EditText passwordbox = findViewById(R.id.password);
        viewModel= new ViewModelProvider(this).get(UserViewModel.class);
        String email = emailbox.getText().toString();
        String password = passwordbox.getText().toString();
        user.setEmail(email);
        user.setPassword(password);
        //logic for log in button
        Button button = findViewById(R.id.LogIn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    viewModel.logUser(user);
                } catch (Exception e) {
                    Log.i("EXCEPTION", Objects.requireNonNull(e.getMessage()));
                }

                //if login is successful we move to the HomeFragment
                //TODO
//                if (viewModel.isLoginValid(response)){
                Intent intent = new Intent(LogActivity.this, MainActivity.class);
                startActivity(intent);
//            } else {
//                      Toast.makeText(getApplicationContext(),"Log in not successful, try again",Toast.LENGTH_SHORT).show();
//                  }
           }
        });

        //logic for create button

        Button createButton = findViewById(R.id.createAccount);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateLogFragment create = new CreateLogFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fllogin, create)
                        .commit();
            }
        });

        //logic for forgetting password button
        Button passwordreset = findViewById(R.id.reset_password);
        passwordreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetPasswordFragment reset = new ResetPasswordFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fllogin, reset)
                        .commit();
            }
        });


    }
}