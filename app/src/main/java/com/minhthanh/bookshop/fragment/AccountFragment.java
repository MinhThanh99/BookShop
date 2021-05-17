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
import com.minhthanh.bookshop.adapter.ViewPagerAdapter_Acount;
import com.minhthanh.bookshop.databinding.FragmentAccountBinding;

public class AccountFragment extends Fragment {

    FragmentAccountBinding binding;
    ViewPagerAdapter_Acount viewPagerAdapter_acount;

    public static AccountFragment newInstance() {

        Bundle args = new Bundle();

        AccountFragment fragment = new AccountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account,container,false);

        viewPagerAdapter_acount = new ViewPagerAdapter_Acount(getFragmentManager());
        binding.viewpagerAccount.setAdapter(viewPagerAdapter_acount);
        // It is used to join TabLayout with ViewPager.
        binding.tablayoutAccount.setupWithViewPager(binding.viewpagerAccount);


        return binding.getRoot();
    }

    private void getFragment(Fragment fragment){
        getFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
    }
}
