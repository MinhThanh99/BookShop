package com.minhthanh.bookshop.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.minhthanh.bookshop.R;
import com.minhthanh.bookshop.fragment.SignInFragment;
import com.minhthanh.bookshop.fragment.SignUpFragment;

public class ViewPagerAdapter_Acount extends FragmentPagerAdapter {


    public ViewPagerAdapter_Acount(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if(position == 0)
            fragment = new SignInFragment();
        if(position == 1)
            fragment = new SignUpFragment();
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
    if(position == 0)
        title = "Sign in";
    else if(position == 1)
        title = "Sign up";
        return title;
    }
}
