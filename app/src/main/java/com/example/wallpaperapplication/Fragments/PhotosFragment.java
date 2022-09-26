package com.example.wallpaperapplication.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.wallpaperapplication.Adapters.PhotoAdapter;
import com.example.wallpaperapplication.Models.Photo;
import com.example.wallpaperapplication.R;
import com.example.wallpaperapplication.WebService.ApiInterface;
import com.example.wallpaperapplication.WebService.RetrofitClient;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PhotosFragment extends Fragment {
    private final String TAG = "Rania";

   // @BindView(R.id.fragmentphotos_progressbar)
  //  ProgressBar progressBar;
    @BindView(R.id.fragmentphotos_recyclerview)
    RecyclerView recyclerView;

    private PhotoAdapter photoAdapter;
    private List<Photo> photos= new ArrayList<>();

    private Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_photos, container , false);
       unbinder = ButterKnife.bind(this, view);
       //RecyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        photoAdapter = new PhotoAdapter(getActivity(), photos);
        recyclerView.setAdapter(photoAdapter);
        //showProgressBar(true);
        getPhotos();
        return view;
    }

    private void getPhotos(){
       RetrofitClient.getInstance().getApi()
               .getPhotos().enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call,
                                   Response<List<Photo>> response) {
                photos.addAll(response.body());
                photoAdapter.notifyDataSetChanged();

                Log.d(TAG, "onResponse:" + response.body().toString());
                //showProgressBar(true);
            }


           @Override
           public void onFailure(Call<List<Photo>> call, Throwable t) {

                        Log.e(TAG, "fail" + t.getMessage());
                       // showProgressBar(false);
           }
       });
    }
  //  private void showProgressBar(boolean isShow){
    //    if(isShow){
     //       progressBar.setVisibility(View.VISIBLE);
     //       recyclerView.setVisibility(View.GONE);
       // }
      //  else{
        //    progressBar.setVisibility(View.GONE);
        //    recyclerView.setVisibility(View.VISIBLE);
       // }
   // }

    @Override
    public void onDestroyView() {

        super.onDestroyView();
        unbinder.unbind();
    }
}