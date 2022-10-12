package com.example.wallpaperapplication.Activities;

import static com.example.wallpaperapplication.R.drawable.*;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.example.wallpaperapplication.Models.Photo;
import com.example.wallpaperapplication.R;
import com.example.wallpaperapplication.Utils.Functions;


import com.example.wallpaperapplication.Utils.GlideApp;
import com.example.wallpaperapplication.Utils.RealmController;
import com.example.wallpaperapplication.WebService.RetrofitClient;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;


import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FullScreenPhotoActivity extends AppCompatActivity {
    private final String TAG = "getPhoto";
    @BindView(R.id.activity_fullscreen_photo_photo)
    ImageView fullscreenPhoto;
    @BindView(R.id.activity_fullscreen_photo_user_avatar)
    CircleImageView userAvatar;
    @BindView(R.id.activity_fullscreen_photo_fab_menu)
    FloatingActionMenu fabMenu;
    @BindView(R.id.activity_fullscreen_photo_fab_favorite)
    FloatingActionButton fabFavorite;
    @BindView(R.id.activity_fullscreen_photo_fab_wallpaper)
    FloatingActionButton fabWallpaper;
    @BindView(R.id.activity_fullscreen_photo_username)
    TextView username;

   // @SuppressLint("ResourceType")
    //@BindView(R.drawable.ic_favorite_check)
    //Drawable icFavorited;


    //@SuppressLint("ResourceType")
    //@BindView(R.drawable.ic_favorite)
    //Drawable icFavorite;


    private RealmController realmController;
    private Bitmap photoBitmap;
    private Photo photo = new Photo();
    private Unbinder unbinder;


    // Get information of photo
   // private Unbinder unbinder = ButterKnife.bind(this);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_photo);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
         unbinder = ButterKnife.bind(this);
        //unbinder = ButterKnife.bind(this, fullscreenPhoto);
        Intent intent = getIntent();
        String photoId =intent.getStringExtra("photoId");
        getPhoto(photoId);
        realmController = new RealmController();
        if (realmController.isPhotoExist(photoId)){
            fabFavorite.setImageResource(ic_favorite);
        }


    }

    private void getPhoto(String id) {
        RetrofitClient.getInstance().getApi().getPhoto(id).enqueue(new Callback<Photo>() {
           @Override
            public void onResponse(Call<Photo> call, Response<Photo> response) {
                photo = response.body();
                updateUI(photo);
               Log.d(TAG, "success"); //+ t.getMessage());
            }

            @Override
            public void onFailure(Call<Photo> call, Throwable t) {
                Log.d(TAG, "fail"); //+ t.getMessage());

            }
        });


    }

    private void updateUI(Photo photo) {
        //make sure that if we have some problems here our app will not crash
        try {
            username.setText(photo.getUser().getUsername());
            GlideApp.with(FullScreenPhotoActivity.this)
                    .load(photo.getUser().getProfileImage().getSmall())
                    .into(userAvatar);

            GlideApp
                    .with(FullScreenPhotoActivity.this)
                    .asBitmap()
                    .load(photo.getUrl().getRegular())
                    .centerCrop()
                    .into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
                        @Override
                        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                            fullscreenPhoto.setImageBitmap(resource);
                            photoBitmap = resource;
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.activity_fullscreen_photo_fab_favorite)
    public void setFabFavorite() {
        //first photo was stored in android phone
        //second photo isn't stored
        if (realmController.isPhotoExist(photo.getId())){
            realmController.deletePhoto(photo);
            fabFavorite.setImageResource(ic_favorite);
            Toast.makeText(FullScreenPhotoActivity.this, "Remove favorite", Toast.LENGTH_LONG).show();
        }else {
            realmController.savePhoto(photo);
            fabFavorite.setImageResource(ic_favorite_check);
            Toast.makeText(FullScreenPhotoActivity.this, "Favorited", Toast.LENGTH_LONG).show();
        }

            fabMenu.close(true);
        }


    @OnClick(R.id.activity_fullscreen_photo_fab_wallpaper)
    public void setFabWallpaper() {
        if(photoBitmap != null){
        if(Functions.setWallpaper(FullScreenPhotoActivity.this, photoBitmap)){
            Toast.makeText(FullScreenPhotoActivity.this, "Set Wallpaper Succefully", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(FullScreenPhotoActivity.this, "Set Wallpaper Fail", Toast.LENGTH_LONG).show();
        }
        super.onDestroy();
        //unbinder.unbind();
    }
        fabMenu.close(true);
    }

}
