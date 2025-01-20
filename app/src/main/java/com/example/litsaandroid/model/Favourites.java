package com.example.litsaandroid.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.time.LocalDateTime;
import java.util.List;

public class Favourites extends BaseObservable implements Parcelable {

    private Long id;

    private Long userId;

    private String displayName;

    private String photoLink;

    private String formattedAddress;

    private String website;

    private String priceLevel;

    private String types;

    public Favourites() {
    }

    public Favourites(String displayName, String photoLink, String formattedAddress, String website, String priceLevel, String types) {
        this.displayName = displayName;
        this.photoLink = photoLink;
        this.formattedAddress = formattedAddress;
        this.website = website;
        this.priceLevel = priceLevel;
        this.types = types;
    }


    protected Favourites(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        if (in.readByte() == 0) {
            userId = null;
        } else {
            userId = in.readLong();
        }
        displayName = in.readString();
        photoLink = in.readString();
        formattedAddress = in.readString();
        website = in.readString();
        priceLevel = in.readString();
        types = in.readString();
    }

    public static final Creator<Favourites> CREATOR = new Creator<Favourites>() {
        @Override
        public Favourites createFromParcel(Parcel in) {
            return new Favourites(in);
        }

        @Override
        public Favourites[] newArray(int size) {
            return new Favourites[size];
        }
    };

    @Bindable
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Bindable
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Bindable
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    @Bindable
    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    @Bindable
    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    @Bindable
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Bindable
    public String getPriceLevel() {
        return priceLevel;
    }

    public void setPriceLevel(String priceLevel) {
        this.priceLevel = priceLevel;
    }

    @Bindable
    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        if (userId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(userId);
        }
        dest.writeString(displayName);
        dest.writeString(photoLink);
        dest.writeString(formattedAddress);
        dest.writeString(website);
        dest.writeString(priceLevel);
        dest.writeString(types);
    }
}



