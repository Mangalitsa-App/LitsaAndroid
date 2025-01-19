package com.example.litsaandroid.user;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.litsaandroid.model.User;
import com.example.litsaandroid.model.UserResponse;
import com.example.litsaandroid.repositories.UserRepository;

public class UserViewModel extends AndroidViewModel {
    private UserRepository userRepository;

    public UserViewModel(@NonNull Application application) throws Exception {
        super(application);
        this.userRepository = new UserRepository(application);
    }

    public LiveData<User> getUser(String token) {
        return userRepository.getUser(token);
    }
    public void addUser (String email, String password, String username){
    userRepository.addUser(email, password, username);
    }
    public UserResponse logUser(String email, String password) throws Exception {
       return userRepository.logUser(email,password);

    }
    public Boolean isLoginValid (UserResponse userResponse){
        if(userResponse.getToken() != null){
            return true;
        }
        return false;
    }
}


