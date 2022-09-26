package com.example.wallpaperapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wallpaperapplication.Models.Collection;
import com.example.wallpaperapplication.R;
import com.example.wallpaperapplication.Utils.GlideApp;
import com.example.wallpaperapplication.Utils.SquareImage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectionAdapter extends BaseAdapter {
    private Context context;
    private List<Collection> collections;

    public CollectionAdapter(Context context, List<Collection> collections) {
        this.context = context;
        this.collections = collections;
    }

    @Override
    public int getCount() {
        return collections.size();
    }

    @Override
    public Object getItem(int position) {
        return collections.get(position);
    }

    @Override
    public long getItemId(int position) {
        return collections.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if(view == null){
           view = LayoutInflater.from(context).inflate(R.layout.collection_item, parent, false);
           holder = new ViewHolder(view);
           view.setTag(holder);
        } else{
            holder = (ViewHolder) view.getTag();
        }
        ButterKnife.bind(this, view);
        Collection collection = collections.get(position);

        if(collection.getTitle()+"" != null){
          holder.title.setText(collection.getTitle());
        }
        holder.totalPhotos.setText(String.valueOf(collection.getTotalPhotos()) + "photos");
        GlideApp
                .with(context)
                .load(collection.getCoverPhoto().getUrl().getRegular())
                .into(holder.collectionPhoto);
        return view;
    }
    static class ViewHolder {
        @BindView(R.id.collection_item_title)
        TextView title;
        @BindView(R.id.collection_item_total_photos)
        TextView totalPhotos;
        @BindView(R.id.collection_item_photo)
        SquareImage collectionPhoto;
        public ViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }
}
