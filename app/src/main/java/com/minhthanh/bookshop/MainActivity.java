package com.minhthanh.bookshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.minhthanh.bookshop.databinding.ActivityMainBinding;
import com.minhthanh.bookshop.home.HomeFragment;
import com.minhthanh.bookshop.img_slider.Image;
import com.minhthanh.bookshop.img_slider.ImageAdapter;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private final static int ID_HOME = 1;
    private final static int ID_EXPLORE = 2;
    private final static int ID_MESSAGE = 3;
    private final static int ID_NOTIFICATION = 4;
    private final static int ID_ACCOUNT = 5;


    private ImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        //final TextView tvSelected = findViewById(R.id.tv_selected);
        binding.tvSelected.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/SourceSansPro-Regular.ttf"));

        //MeowBottomNavigation bottomNavigation = findViewById(R.id.bottomNavigation);

        binding.bottomNavigation.add(new MeowBottomNavigation.Model(ID_HOME, R.drawable.ic_home));
        binding.bottomNavigation.add(new MeowBottomNavigation.Model(ID_EXPLORE, R.drawable.ic_explore));
        binding.bottomNavigation.add(new MeowBottomNavigation.Model(ID_MESSAGE, R.drawable.ic_message));
        binding.bottomNavigation.add(new MeowBottomNavigation.Model(ID_NOTIFICATION, R.drawable.ic_notification));
        binding.bottomNavigation.add(new MeowBottomNavigation.Model(ID_ACCOUNT, R.drawable.ic_account));

        binding.bottomNavigation.setCount(ID_NOTIFICATION, "115");

        binding.bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                switch (item.getId()) {
                    case ID_HOME:

                        getFragment(HomeFragment.newInstance());
                        break;
                    case ID_EXPLORE:
                        getFragment(AccountFragment.newInstance());

                        break;
                    case ID_MESSAGE:
                        getFragment(CartFragment.newInstance());

                        break;
                    case ID_NOTIFICATION:
                        getFragment(Fragment4.newInstance());

                        break;
                    case ID_ACCOUNT:
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
                        name = "HOME";
                        break;
                    case ID_EXPLORE:
                        name = "EXPLORE";
                        break;
                    case ID_MESSAGE:
                        name = "MESSAGE";
                        break;
                    case ID_NOTIFICATION:
                        name = "NOTIFICATION";
                        break;
                    case ID_ACCOUNT:
                        name = "ACCOUNT";
                        break;
                    default:
                        name = "";
                }
                binding.tvSelected.setText(getString(R.string.main_page_selected, name));
            }
        });

        binding.bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                Toast.makeText(MainActivity.this, "reselected item : " + item.getId(), Toast.LENGTH_SHORT).show();
            }
        });


        binding.bottomNavigation.setCount(ID_NOTIFICATION, "115");

        binding.bottomNavigation.show(ID_NOTIFICATION,true);




    }

    private void getFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
    }

}