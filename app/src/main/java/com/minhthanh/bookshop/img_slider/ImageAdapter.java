package com.minhthanh.bookshop.img_slider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.minhthanh.bookshop.R;
import com.minhthanh.bookshop.databinding.ItemSliderBinding;

import java.util.List;

public class ImageAdapter extends PagerAdapter {

    ItemSliderBinding binding;
    Context context;
    List<Image> imageList;

    public ImageAdapter(Context context, List<Image> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_slider,container,false);

        ImageView img = view.findViewById(R.id.img_slider);
        Image image = imageList.get(position);
        if(image != null){
            Glide.with(context).load(image.getResourceID()).into(img);
        }

        //add view to viewgroup
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        if(imageList != null)
            return imageList.size();
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //remove view;
        container.removeView((View) object);
    }
}
