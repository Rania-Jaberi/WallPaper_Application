package com.example.wallpaperapplication.Models;

import com.google.gson.annotations.SerializedName;

public class Collection {
    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("total_photos")
    private int totalPhotos;

    @SerializedName("cover_photo")
    private Photo coverPhoto = new Photo();

    public Collection(String id, String title, String description, int totalPhotos, Photo coverPhoto, User user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.totalPhotos = totalPhotos;
        this.coverPhoto = coverPhoto;
        this.user = user;
    }

    @SerializedName("user")
    private User user = new User();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
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

    @Override
    public String toString() {
        return "Collection{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", totalPhotos=" + totalPhotos +
                ", coverPhoto=" + coverPhoto +
                ", user=" + user +
                '}';
    }
}
