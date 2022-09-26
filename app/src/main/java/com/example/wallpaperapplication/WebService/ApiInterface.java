package com.example.wallpaperapplication.WebService;

import com.example.wallpaperapplication.Models.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface  ApiInterface {
    @GET("photos/?client_id=u5gUoExdE7e5lTH1f9p_OARJe1c_Z2ebm3WDYLFCjCE")
    Call<List<Photo>> getPhotos();
}
