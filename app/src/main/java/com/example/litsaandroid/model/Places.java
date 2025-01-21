package com.example.litsaandroid.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.litsaandroid.BR;

import java.util.List;

public class Places extends BaseObservable implements Parcelable {
    private String id;

    private String displayName;

    private String img;

    private String formattedAddress;

    private String websiteUri;

    private String priceLevel;

    private List<String> types;

    public Places() {
    }

    public Places(String id, String displayName, String img, String formattedAddress, String websiteUri, String priceLevel, List<String> types) {
        this.id = id;
        this.displayName = displayName;
        this.img = img;
        this.formattedAddress = formattedAddress;
        this.websiteUri = websiteUri;
        this.priceLevel = priceLevel;
        this.types = types;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Bindable
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Bindable
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Bindable
    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    @Bindable
    public String getWebsiteUri() {
        return websiteUri;
    }

    public void setWebsiteUri(String websiteUri) {
        this.websiteUri = websiteUri;
    }

    @Bindable
    public String getPriceLevel() {
        return priceLevel;
    }

    public void setPriceLevel(String priceLevel) {
        this.priceLevel = priceLevel;
    }

    @Bindable
    public List<String> getTypes() {
        return types;
    }

    @Bindable
    public String getTypesAsString(){
        String listStrings = getTypes().toString();
        listStrings= listStrings.replace("[", "")
                .replace("]", "")
                .replace("_", " ")
                .replace(",", "\n");

        return listStrings;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
    dest.writeString(id);
    dest.writeString(displayName);
    dest.writeString(img);
    dest.writeString(formattedAddress);
    dest.writeString(websiteUri);
    dest.writeString(priceLevel);
    dest.writeList(types);
    }
    protected Places (Parcel in){
    id = in.readString();
    displayName = in.readString();
    img = in.readString();
    formattedAddress = in.readString();
    websiteUri = in.readString();
    priceLevel = in.readString();
    types = in.createStringArrayList();
    }
    private static final Creator<Places> CREATOR = new Creator<Places>() {
        @Override
        public Places createFromParcel(Parcel in) {
            return new Places(in);
        }

        @Override
        public Places[] newArray(int size) {
            return new Places[size];
        }
    };
}