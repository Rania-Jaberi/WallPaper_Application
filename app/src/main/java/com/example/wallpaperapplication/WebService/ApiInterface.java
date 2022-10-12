package com.example.wallpaperapplication.WebService;

import com.example.wallpaperapplication.Models.Collection;
import com.example.wallpaperapplication.Models.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface  ApiInterface {
    @GET("photos/?client_id=u5gUoExdE7e5lTH1f9p_OARJe1c_Z2ebm3WDYLFCjCE")
    Call<List<Photo>> getPhotos();

    @GET ("collections/?client_id=u5gUoExdE7e5lTH1f9p_OARJe1c_Z2ebm3WDYLFCjCE")
    Call<List<Collection>> getCollections();

    @GET("collections/{id}/?client_id=u5gUoExdE7e5lTH1f9p_OARJe1c_Z2ebm3WDYLFCjCE")
    Call<Collection> getInformaionOfCollection(@Path("id") String id);

    @GET("collections/{id}/photos/?client_id=u5gUoExdE7e5lTH1f9p_OARJe1c_Z2ebm3WDYLFCjCE")
    Call<List<Photo>> getPhotosOfCollection(@Path("id") int id);

    @GET("photos/{id}/?client_id=u5gUoExdE7e5lTH1f9p_OARJe1c_Z2ebm3WDYLFCjCE")
    Call<Photo> getPhoto(@Path("id") String id);
}
