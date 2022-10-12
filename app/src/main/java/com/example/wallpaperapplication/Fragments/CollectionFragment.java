package com.example.wallpaperapplication.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wallpaperapplication.Adapters.PhotoAdapter;
import com.example.wallpaperapplication.Models.Collection;
import com.example.wallpaperapplication.Models.Photo;
import com.example.wallpaperapplication.R;
import com.example.wallpaperapplication.Utils.GlideApp;
import com.example.wallpaperapplication.WebService.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CollectionFragment extends Fragment {
    private final String TAG = "jet";
    @BindView(R.id.fragment_collection_linearlayout)
    LinearLayout linearLayout;

    @BindView(R.id.fragment_collection_recyclerview)
    RecyclerView PhotorecyclerView;
    @BindView(R.id.fragment_collection_user_avatar)
    CircleImageView userAvatar;
    @BindView(R.id.fragment_collection_username)
    TextView username;
    //@BindView(R.id.collection_item_framellayout)
    //FrameLayout frameLayout;
    //TextView title =(frameLayout).findViewById(R.id.collection_item_title);
    @BindView(R.id.fragment_collection_textview)
    TextView title;
    @BindView(R.id.fragment_collection_description)
    TextView description;

    private List<Photo> photos = new ArrayList<>();
    private PhotoAdapter photoAdapter;

    private Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
         View view= inflater.inflate(R.layout.fragment_collection, container, false);
        unbinder = ButterKnife.bind(this, view);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(requireActivity());
        PhotorecyclerView.setLayoutManager(linearLayoutManager);
        photoAdapter = new PhotoAdapter(getActivity(), photos);
        PhotorecyclerView.setAdapter(photoAdapter);


        Bundle bundle = getArguments();
        String collectionId = bundle.getString("collectionId");
        Log.d(TAG, collectionId);
        getInformationOfCollection(collectionId);
        getPhotosOfCollection(collectionId);

        return view;}

    private  void getInformationOfCollection(final String collectionId){
        RetrofitClient.getInstance().getApi()
                .getInformaionOfCollection(collectionId).enqueue(new Callback<Collection>() {
                    @Override
                    public void onResponse(Call<Collection> call,
                                           Response<Collection> response) {
                        Collection collection = response.body();
                        title.setText(collection.getTitle());
                        description.setText(collection.getDescription());
                        username.setText(collection.getUser().getUsername());
                        GlideApp
                                .with(getActivity())
                                .load(collection.getUser().getProfileImage().getSmall())
                                .into(userAvatar);
                        Log.d(TAG, "onResponse:" + response.body().toString());
                        //showProgressBar(true);
                    }

                    @Override
                    public void onFailure(Call<Collection> call, Throwable t) {

                        Log.e(TAG, "fail" + t.getMessage());
                        // showProgressBar(false);
                    }
                });

    }




    private void getPhotosOfCollection(String collectionId){
        RetrofitClient.getInstance().getApi()
                .getPhotosOfCollection(Integer.parseInt(collectionId)).enqueue(new Callback<List<Photo>>() {
                    @Override
                    public void onResponse(Call<List<Photo>> call,
                                           Response<List<Photo>> response) {

                        Log.d(TAG, "Loading successfully, size: " + response.body().size());
                        for(Photo photo: response.body()){
                            photos.add(photo);
                            Log.d(TAG, photo.getUrl().getFull());
                        }
                        photoAdapter.notifyDataSetChanged();
                        // showProgressBar(true);
                    }

                    @Override
                    public void onFailure(Call<List<Photo>> call, Throwable t) {

                        Log.d(TAG, "Fail: " + t.getMessage());
                        //  showProgressBar(false);
                    }
                });

    }




    @Override
    public void onDestroyView() {

        super.onDestroyView();

    }
}
