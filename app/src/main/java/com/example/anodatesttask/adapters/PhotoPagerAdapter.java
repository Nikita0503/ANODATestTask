package com.example.anodatesttask.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.anodatesttask.R;

import java.util.ArrayList;

public class PhotoPagerAdapter extends PagerAdapter {

    private Context mContext;
    private ArrayList<String> mPhotos;

    public PhotoPagerAdapter(Context context) {
        mContext = context;
        mPhotos = new ArrayList<String>();
    }

    public void addPhotos(ArrayList<String> photos){
        mPhotos.clear();
        mPhotos.addAll(photos);
        notifyDataSetChanged();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_photo_pager, null);
        ImageView imageView = view.findViewById(R.id.imageviewPhoto);
        Glide
                .with(mContext)
                .load(mPhotos.get(position))
                .into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        container.removeView((View) view);
    }

    @Override
    public int getCount () {
        return mPhotos.size();
    }

    @Override
    public boolean isViewFromObject (@NonNull View view, @NonNull Object object){
        return object == view;
    }
}
