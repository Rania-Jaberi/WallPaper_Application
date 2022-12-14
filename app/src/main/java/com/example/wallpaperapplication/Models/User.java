package com.example.wallpaperapplication.Models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class User extends RealmObject{
    @SerializedName("id")
    private String id;
    @SerializedName("username")
    private String username;
    @SerializedName("profile_image")
    private ProfileImage profile_image = new ProfileImage();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ProfileImage getProfileImage() {
        return profile_image;
    }

    public void setProfileImage(ProfileImage profileImage) {
        this.profile_image = profileImage;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", profile_image=" + profile_image +
                '}';
    }
}
