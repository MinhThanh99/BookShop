package com.minhthanh.bookshop.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import model.Book;
import model.Category;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface GetCategoryApi {
    //http://demo8468432.mockable.io/GetCategory


    @GET("GetCategory")
    Call<ArrayList<Category>> getCategory();
}
