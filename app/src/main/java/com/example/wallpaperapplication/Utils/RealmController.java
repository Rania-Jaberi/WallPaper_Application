package com.example.wallpaperapplication.Utils;

import android.app.Application;

import com.example.wallpaperapplication.Models.Photo;

import java.util.List;

import io.realm.Realm;

public class RealmController {
    private final Realm realm;

    public RealmController() {
        realm = Realm.getDefaultInstance();
    }
    public void savePhoto (Photo photo){
        realm.beginTransaction();
        realm.copyToRealm(photo);
        realm.commitTransaction();
    }

    public void deletePhoto(Photo photo){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                //return the photo by id
                Photo resultPhoto = realm.where(Photo.class).equalTo("id", photo.getId()).findFirst();
                resultPhoto.deleteFromRealm();
            }
        });

    }
    public boolean isPhotoExist(String photoId){
        Photo resultPhoto = realm.where(Photo.class).equalTo("id", photoId).findFirst();
        if(resultPhoto == null)
            return false;
        return true;

    }

    public List<Photo> getPhotos(){
        return realm.where(Photo.class).findAll();
    }

}
