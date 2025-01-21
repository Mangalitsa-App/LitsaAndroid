package com.example.litsaandroid.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.litsaandroid.BR;

public class User extends BaseObservable implements Parcelable {

    long id;
    private String display_name;
    private String email;
    private String password;

    public User() {
    }

    public User(long id, String displayName, String email) {
        this.id = id;
        this.display_name = displayName;
        this.email = email;
    }

    public User(String name, String email, String password) {
        this.display_name = name;
        this.email = email;
        this.password = password;
    }

    public User(String password) {
        this.password = password;
    }

    @Bindable
    public String getName() {
        return display_name;
    }

    public void setName(String name) {
        this.display_name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return display_name;
    }

    public void setDisplayName(String displayName) {
        this.display_name = displayName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(display_name);
        dest.writeString(password);
    }

    protected User(Parcel in) {
    display_name = in.readString();
    email= in.readString();
    password = in.readString();
    }

    private static final Creator<User> CREATOR = new Creator<User>() {

        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
