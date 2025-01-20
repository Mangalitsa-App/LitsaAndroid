package com.example.litsaandroid.ui.mainActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.litsaandroid.R;
import com.example.litsaandroid.model.TokenStorage;
import com.example.litsaandroid.model.UserResponse;
import com.example.litsaandroid.user.LogActivity;
import com.example.litsaandroid.user.UserViewModel;

import java.util.Objects;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            TokenStorage tokenStorage;
            try {
                tokenStorage = new TokenStorage(this);
                String token = tokenStorage.getToken();

                if (token != null && !token.isEmpty()) {
                    navigateToHome();
                } else {
                    navigateToLog();
                }
            } catch (Exception e) {
                Log.i("EXCEPTION", Objects.requireNonNull(e.getMessage()));
                navigateToLog();
            }
        }, 3000);

        // Check for stored token
        TokenStorage tokenStorage;
        UserResponse userResponse = new UserResponse();
        try {
//            tokenStorage = new TokenStorage(this);
            String token = userResponse.getToken();

            if (token != null && !token.isEmpty()) {
                // Token exists, navigate to Home Activity
                navigateToHome();
            } else {
                // No token, navigate to Log Activity
                navigateToLog();
            }
        } catch (Exception e) {
           Log.i("EXCEPTION", Objects.requireNonNull(e.getMessage()));
            // If there's an error, treat it as no token and navigate to Log Activity
            navigateToLog();
        }
    }

    private void navigateToHome() {
        Intent intent = new Intent(Splash.this, MainActivity.class);
        startActivity(intent);
        finish(); // Close Splash Activity
    }

    private void navigateToLog() {
        Intent intent = new Intent(Splash.this, LogActivity.class);
        startActivity(intent);
        finish(); // Close Splash Activity
    }
    }
