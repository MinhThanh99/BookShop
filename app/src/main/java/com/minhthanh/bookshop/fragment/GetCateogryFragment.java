package com.minhthanh.bookshop.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.minhthanh.bookshop.R;
import com.minhthanh.bookshop.api.GetCategoryApi;
import com.minhthanh.bookshop.databinding.FragmentHomeBinding;
import com.minhthanh.bookshop.databinding.HomeItemCategoryBinding;

import java.util.ArrayList;

import model.Category;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetCateogryFragment extends Fragment {

    Context context;
    FragmentHomeBinding binding;
    ArrayList<Category> categoryList;

    public static GetCateogryFragment newInstance() {
        
        Bundle args = new Bundle();
        
        GetCateogryFragment fragment = new GetCateogryFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private void getCategory(){
        // on below line we are creating a retrofit builder and passing our base url
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://demo8468432.mockable.io/")
                // on below line we are calling add Converter factory as Gson converter factory.
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();

        // below line is to create an instance for our retrofit api class.
        GetCategoryApi getCategoryApi = retrofit.create(GetCategoryApi.class);
        // on below line we are calling a method to get all the courses from API.
        Call<ArrayList<Category>> call = getCategoryApi.getCategory();
        // on below line we are calling method to enqueue and calling all the data from array list.
        call.enqueue(new Callback<ArrayList<Category>>() {

            @Override
            public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                // inside on response method we are checking if the response is success or not.
                if (response.isSuccessful()) {
                    // on successful we are hiding our progressbar.
                    binding.loading.setVisibility(View.GONE);
                    Toast.makeText(context, "thanh cong", Toast.LENGTH_LONG).show();
                    // below line is to add our data from api to our array list.
                    categoryList = response.body();

                    //send
                    HomeFragment fragment = new HomeFragment();
                    Intent intent = new Intent();
                    intent.putExtra("categolist", categoryList);


                    getFragmentManager().beginTransaction().add(R.id.container, fragment).commit();

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {
                // in the method of on failure we are displaying a toast message for fail to get data.
                Toast.makeText(context, "Fail to get data", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
