package com.minhthanh.bookshop.api;


import java.util.ArrayList;

import model.Book;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetBookApi {

    //https://demo8468432.mockable.io/GetBook

    @GET("GetBook")
    Call<ArrayList<Book>>  getBook();
}
