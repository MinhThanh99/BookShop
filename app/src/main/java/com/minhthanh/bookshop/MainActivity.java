package com.minhthanh.bookshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.minhthanh.bookshop.databinding.ActivityMainBinding;
import com.minhthanh.bookshop.fragment.AccountFragment;
import com.minhthanh.bookshop.fragment.CartFragment;
import com.minhthanh.bookshop.fragment.Fragment4;
import com.minhthanh.bookshop.fragment.Fragment5;
import com.minhthanh.bookshop.home.HomeFragment;
import com.minhthanh.bookshop.home.img_slider.SlideAdapter;

import java.util.ArrayList;

import model.Book;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private final static int ID_HOME = 1;
    private final static int ID_MESSAGE = 2;
    private final static int ID_NOTIFICATION = 3;
    private final static int ID_ACCOUNT = 4;
    private final static int ID_CART = 5;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        //final TextView tvSelected = findViewById(R.id.tv_selected);
        //set font text
        //binding.tvSelected.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/SourceSansPro-Regular.ttf"));


        //MeowBottomNavigation bottomNavigation = findViewById(R.id.bottomNavigation);

        binding.bottomNavigation.add(new MeowBottomNavigation.Model(ID_HOME, R.drawable.ic_home));
        binding.bottomNavigation.add(new MeowBottomNavigation.Model(ID_MESSAGE, R.drawable.ic_message));
        binding.bottomNavigation.add(new MeowBottomNavigation.Model(ID_NOTIFICATION, R.drawable.ic_notification));
        binding.bottomNavigation.add(new MeowBottomNavigation.Model(ID_ACCOUNT, R.drawable.ic_account));
        binding.bottomNavigation.add(new MeowBottomNavigation.Model(ID_CART, R.drawable.ic_outline_shopping_cart_24));

        binding.bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                switch (item.getId()) {
                    case ID_HOME:

                        getFragment(HomeFragment.newInstance());
                        break;

                    case ID_MESSAGE:
                        getFragment(CartFragment.newInstance());

                        break;
                    case ID_NOTIFICATION:
                        getFragment(Fragment4.newInstance());

                        break;
                    case ID_ACCOUNT:
                        getFragment(AccountFragment.newInstance());


                        break;
                    case ID_CART:
                        getFragment(Fragment5.newInstance());

                        break;
                    default:
                        break;
                }
                Toast.makeText(MainActivity.this, "clicked item : " + item.getId(), Toast.LENGTH_SHORT).show();
            }
        });

        binding.bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

                Toast.makeText(MainActivity.this, "showing item : " + item.getId(), Toast.LENGTH_SHORT).show();

                String name;
                switch (item.getId()) {
                    case ID_HOME:
                        getFragment(HomeFragment.newInstance());
                        name = "HOME";
                        break;
                    case ID_MESSAGE:
                        name = "MESSAGE";
                        break;
                    case ID_NOTIFICATION:
                        name = "NOTIFICATION";
                        break;
                    case ID_ACCOUNT:
                        getFragment(AccountFragment.newInstance());
                        name = "ACCOUNT";
                        break;
                    case ID_CART:
                        name = "EXPLORE";
                        break;
                    default:
                        name = "";
                }

            }
        });

        binding.bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                Toast.makeText(MainActivity.this, "reselected item : " + item.getId(), Toast.LENGTH_SHORT).show();
            }
        });


        //Đếm thông báo
        binding.bottomNavigation.setCount(ID_NOTIFICATION, "0");

        //Hiển thị đầu tiên
        binding.bottomNavigation.show(ID_HOME,true);




    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void getFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
    }

}