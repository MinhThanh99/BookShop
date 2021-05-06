package com.minhthanh.bookshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.minhthanh.bookshop.databinding.Fragment5Binding;

public class Fragment5 extends Fragment {
    Fragment5Binding binding;

    public static Fragment5 newInstance() {

        Bundle args = new Bundle();

        Fragment5 fragment = new Fragment5();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_5,container,false);

        return binding.getRoot();
    }
}
