package com.example.wallpaperapplication.Models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Photo extends RealmObject{
    @SerializedName("id")
    @PrimaryKey
    private String id;
    @SerializedName("description")
    private String description;
    @SerializedName("urls")
    private PhotoUrl urls = new PhotoUrl();
    @SerializedName("user")
    private User user = new User();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PhotoUrl getUrl() {
        return urls;
    }

    public void setUrl(PhotoUrl url) {
        this.urls = url;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", urls=" + urls +
                ", user=" + user +
                '}';
    }
}
