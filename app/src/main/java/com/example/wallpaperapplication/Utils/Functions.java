package com.example.wallpaperapplication.Utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.wallpaperapplication.Fragments.CollectionFragment;
import com.example.wallpaperapplication.R;

public class Functions {
    public static void changeMainFragment(FragmentActivity activity, CollectionFragment collectionFragment) {
    }
     public static void changeMainFragment( FragmentActivity fragmentActivity, Fragment fragment){
       fragmentActivity.getSupportFragmentManager()
           .beginTransaction()
            .replace(R.id.fragment_collections_gridview, fragment)
           .commit();
    }

    public static void changeMainFragmentWithBack( FragmentActivity fragmentActivity, Fragment fragment){
        fragmentActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_collections_gridview, fragment)
                .addToBackStack(null)
                .commit();
    }

}
