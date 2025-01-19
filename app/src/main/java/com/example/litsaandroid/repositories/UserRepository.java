package com.example.litsaandroid.repositories;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.litsaandroid.model.TokenStorage;
import com.example.litsaandroid.model.User;
import com.example.litsaandroid.model.UserResponse;
import com.example.litsaandroid.service.RetrofitInstance;
import com.example.litsaandroid.service.UserAPIService;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private Application application;
    private final MutableLiveData<User> mutableLiveData = new MutableLiveData<>();
    private UserAPIService userAPIService;


    public UserRepository(Application application) {
        this.application = application;
        userAPIService = RetrofitInstance.getService().create(UserAPIService.class);
    }


    public MutableLiveData<User> getUser(String token) {
        Call<User> call = userAPIService.getUser(token);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.i("API Response", response.body().toString());
                    User user = response.body();
                    mutableLiveData.setValue(user);
                } else {
                    Log.e("API Response", "Response unsuccessful or empty");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("API Error", Objects.requireNonNull(t.getMessage()));
            }
        });
        return mutableLiveData;
    }

    public void addUser(User user) {
        Call call = userAPIService.create(user);
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call call, Response response) {
                Toast.makeText(application.getApplicationContext(), "You're successfully registered", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(application.getApplicationContext(), "Unable to create new User", Toast.LENGTH_SHORT).show();
                Log.e("POST onFailure", t.getMessage());
            }
        });
    }

    public UserResponse logUser(User user) throws Exception {
        TokenStorage tokenStorage = new TokenStorage(application.getApplicationContext());
        UserResponse userResponse= new UserResponse();
        Call call = userAPIService.loginToken(user);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String token = response.body().getToken(); // Extract token
                    tokenStorage.saveToken(token);// Save token securely
                    userResponse.setToken(token);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(application.getApplicationContext(), "User email or password incorrect", Toast.LENGTH_SHORT).show();
                Log.e("POST onFailure", t.getMessage());
            }
        });

        return userResponse;
    }

}
