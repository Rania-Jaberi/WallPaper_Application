package com.example.wallpaperapplication.Models;

import com.google.gson.annotations.SerializedName;

public class ProfileImage {
    @SerializedName("small")
    private String small;
    @SerializedName("meduim")
    private String meduim;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMeduim() {
        return meduim;
    }

    public void setMeduim(String meduim) {
        this.meduim = meduim;
    }
}
