package com.example.litsaandroid.user;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.litsaandroid.model.User;
import com.example.litsaandroid.model.UserRequest;
import com.example.litsaandroid.repositories.UserRepository;

import retrofit2.Callback;

public class UserViewModel extends AndroidViewModel {
    private UserRepository userRepository;

    public UserViewModel(@NonNull Application application) throws Exception {
        super(application);
        this.userRepository = new UserRepository(application);
    }


    public User getUser() throws Exception {
        return userRepository.getUser();

    }
    public void addUser (User user){
    userRepository.addUser(user);
    }
    public void logUser(UserRequest user) throws Exception {
        userRepository.logUser(user);

    }

//    public Boolean isLoginValid (UserResponse userResponse){
//        return userResponse.getToken() != null;
//    }


    public User editUser(User user){
        return userRepository.editUser(user);
    }
    public void resetPassword(String email){
        userRepository.resetPassword(email);
    }
}


