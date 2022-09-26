package com.example.wallpaperapplication.Models;

import com.google.gson.annotations.SerializedName;

public class PhotoUrl {
    @SerializedName("full")
    private String full;
    @SerializedName("regular")
    private String regular;

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }

    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }

    @Override
    public String toString() {
        return "PhotoUrl{" +
                "full='" + full + '\'' +
                ", regular='" + regular + '\'' +
                '}';
    }
}
