package com.example.wallpaperapplication.Models;

import com.google.gson.annotations.SerializedName;

public class Collection {
    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private int title;

    @SerializedName("description")
    private int description;

    @SerializedName("total_photos")
    private int totalPhotos;

    @SerializedName("cover_photo")
    private Photo coverPhoto = new Photo();

    @SerializedName("user")
    private User user = new User();

    public Collection(int id, int title, int description, int totalPhotos, Photo coverPhoto, User user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.totalPhotos = totalPhotos;
        this.coverPhoto = coverPhoto;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    public int getTotalPhotos() {
        return totalPhotos;
    }

    public void setTotalPhotos(int totalPhotos) {
        this.totalPhotos = totalPhotos;
    }

    public Photo getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(Photo coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
