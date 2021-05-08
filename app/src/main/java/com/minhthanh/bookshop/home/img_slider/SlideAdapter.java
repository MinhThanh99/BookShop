package com.minhthanh.bookshop.home.img_slider;

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

public class SlideAdapter extends PagerAdapter {

    ItemSliderBinding binding;
    Context context;
    List<Slide> slideList;

    public SlideAdapter(Context context, List<Slide> slideList) {
        this.context = context;
        this.slideList = slideList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_slider,container,false);

        ImageView img = view.findViewById(R.id.img_slider);
        Slide slide = slideList.get(position);
        if(slide != null){
            Glide.with(context).load(slide.getThumb()).into(img);
        }

        //add view to viewgroup
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        if(slideList != null)
            return slideList.size();
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
