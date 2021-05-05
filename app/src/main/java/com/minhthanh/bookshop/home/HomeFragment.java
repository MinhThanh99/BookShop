package com.minhthanh.bookshop.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.minhthanh.bookshop.R;
import com.minhthanh.bookshop.databinding.FragmentHomeBinding;
import com.minhthanh.bookshop.img_slider.Image;
import com.minhthanh.bookshop.img_slider.ImageAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    private ImageAdapter imageAdapter;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,container,false);
        binding.tvHome.setText("Home");

        imageAdapter = new ImageAdapter(getContext(), getListImage());
        binding.viewpager.setAdapter(imageAdapter);

        binding.circleindicator.setViewPager(binding.viewpager);
        imageAdapter.registerDataSetObserver(binding.circleindicator.getDataSetObserver());

        return binding.getRoot();
    }

    private List<Image> getListImage(){
        List<Image> imageList = new ArrayList<>();
        imageList.add(new Image(R.drawable.heaven));
        imageList.add(new Image(R.drawable.heaven));
        imageList.add(new Image(R.drawable.heaven));
        imageList.add(new Image(R.drawable.heaven));
        imageList.add(new Image(R.drawable.heaven));

        return imageList;
    }
}
