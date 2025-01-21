package com.example.litsaandroid.repositories;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.litsaandroid.model.TokenStorage;
import com.example.litsaandroid.model.User;
import com.example.litsaandroid.model.UserRequest;
import com.example.litsaandroid.model.UserResponse;
import com.example.litsaandroid.service.RetrofitInstance;
import com.example.litsaandroid.service.UserAPIService;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private Application application;
    private UserAPIService userAPIService;

    private UserResponse userResponse;
    public UserRepository(Application application) {
        this.application = application;
        userAPIService = RetrofitInstance.getService().create(UserAPIService.class);
    }



    public User getUser()  {
        String token = userResponse.getToken();

        User newUser = new User();
        Call<User> call = userAPIService.getUser(token);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.i("API Response", response.body().toString());
                    User user = response.body();
                    newUser.setEmail(user.getEmail());
                    newUser.setName(user.getName());
                    newUser.setId(user.getId());
                } else {
                    Log.e("API Response", "Response unsuccessful or empty");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("API Error", Objects.requireNonNull(t.getMessage()));
            }
        });
        return newUser;
    }

    public void addUser(User user) {
        Call call = userAPIService.create(user);
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(application.getApplicationContext(), "You're successfully registered", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(application.getApplicationContext(), "Unable to create new User", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("POST onFailure", t.getMessage());
            }
        });
    }

    public void logUser(UserRequest user)  {
        UserResponse userResponse= new UserResponse();
        Call call = userAPIService.loginToken(user);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    UserResponse ur = response.body(); // Extract token
                    userResponse.setToken(ur.getToken());
                    String token = ur.getToken();
                    Toast.makeText(application.getApplicationContext(), "your token is " + token, Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(application.getApplicationContext(), "User email or password incorrect", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("POST onFailure", t.getMessage());
            }
        });

    }
    public User editUser (User user){
        User updatedUser = new User();
        Call call = userAPIService.editUser(user.getId(), user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
               User useResponse = response.body();
                updatedUser.setDisplayName(useResponse.getDisplayName());
                updatedUser.setPassword(useResponse.getPassword());
                    Toast.makeText(application.getApplicationContext(), "User updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(application.getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("POST onFailure", t.getMessage());
            }
        });
        return updatedUser;
    }
    public void resetPassword(String email) {
        Call call = userAPIService.resetPassword(email);
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(application.getApplicationContext(), "You will receive an email shortly", Toast.LENGTH_SHORT).show();
                } else{
//                    Toast.makeText(application.getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("POST onFailure", t.getMessage());
            }
        });
    }
}
