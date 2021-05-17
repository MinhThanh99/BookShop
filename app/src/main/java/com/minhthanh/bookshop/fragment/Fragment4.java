package com.minhthanh.bookshop.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.minhthanh.bookshop.R;
import com.minhthanh.bookshop.databinding.Fragment4Binding;

public class Fragment4 extends Fragment {

    Fragment4Binding binding;

    public static Fragment4 newInstance() {

        Bundle args = new Bundle();

        Fragment4 fragment = new Fragment4();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_4,container,false);

        return binding.getRoot();
    }
}
