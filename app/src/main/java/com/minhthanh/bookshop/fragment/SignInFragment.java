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
import com.minhthanh.bookshop.databinding.FragmentBookDetailBinding;
import com.minhthanh.bookshop.databinding.FragmentSignInBinding;

public class SignInFragment extends Fragment {

    FragmentSignInBinding binding;
    public static SignInFragment newInstance() {

        Bundle args = new Bundle();

        SignInFragment fragment = new SignInFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in,container,false);
        return binding.getRoot();
    }

    public static class BookDetailFragment extends Fragment {

        FragmentBookDetailBinding binding;

        public static Fragment newInstance() {

            Bundle args = new Bundle();
            BookDetailFragment fragment = new BookDetailFragment();
            fragment.setArguments(args);
            return fragment;
        }


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_book_detail,container,false);

            return binding.getRoot();
        }
    }
}
