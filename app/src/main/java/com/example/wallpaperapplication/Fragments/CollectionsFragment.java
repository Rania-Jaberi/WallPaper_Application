package com.example.wallpaperapplication.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.wallpaperapplication.Adapters.CollectionAdapter;

import com.example.wallpaperapplication.Models.Collection;
import com.example.wallpaperapplication.R;
import com.example.wallpaperapplication.WebService.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CollectionsFragment extends Fragment {
    private final String TAG = "Rania";
    @BindView(R.id.fragment_collections_gridview)
    GridView gridView;


private CollectionAdapter collectionAdapter;
private List<Collection> collections = new ArrayList<>();
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_collections, container , false);
        unbinder = ButterKnife.bind(this, view);
       collectionAdapter = new CollectionAdapter(getActivity(), collections);
       gridView.setAdapter(collectionAdapter);
       getCollections();
        return view;
    }


    private void getCollections(){
        RetrofitClient.getInstance().getApi()
                .getCollections().enqueue(new Callback<List<Collection>>() {
                    @Override
                    public void onResponse(Call<List<Collection>> call,
                                           Response<List<Collection>> response) {
                        collections.addAll(response.body());
                        collectionAdapter.notifyDataSetChanged();

                        Log.d(TAG, "onResponse:" + response.body().toString());
                        //showProgressBar(true);
                    }

                    @Override
                    public void onFailure(Call<List<Collection>> call, Throwable t) {

                        Log.e(TAG, "fail" + t.getMessage());
                        // showProgressBar(false);
                    }
                });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}