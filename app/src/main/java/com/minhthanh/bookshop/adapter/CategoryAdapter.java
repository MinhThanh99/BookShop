package com.minhthanh.bookshop.adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.minhthanh.bookshop.R;
import com.minhthanh.bookshop.api.GetBookApi;
import com.minhthanh.bookshop.databinding.HomeItemBookBinding;
import com.minhthanh.bookshop.databinding.HomeItemCategoryBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import model.Book;
import model.Category;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    // creating a variable for our array list and context.
    private Context context;
    private ArrayList<Category> categoryList;

    BookAdapter bookAdapter;
    private ArrayList<Book> bookList;

    HomeItemCategoryBinding binding;




    public CategoryAdapter(Context context, ArrayList<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;

//        getBookList();
    }



    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.home_item_category,parent,false);
        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(view);


        return categoryViewHolder;
    }

    String linkBook = "http://demo8468432.mockable.io/GetBook";
    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

        // Set the data to textview from our modal class.
        Category  category = categoryList.get(position);

        holder.tvNameCategory.setText(category.getName_category());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://demo8468432.mockable.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetBookApi getBookApi = retrofit.create(GetBookApi.class);
        Call<ArrayList<Book>> call = getBookApi.getBook();
        call.enqueue(new Callback<ArrayList<Book>>() {
            @Override
            public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response) {
                if (response.isSuccessful()) {
                    bookList = response.body();
                    for (int i = 0; i < bookList.size(); i++) {
                        bookAdapter = new BookAdapter(context, bookList);
                        // below line is to set layout manager for our recycler view.
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
                        // setting layout manager for our recycler view.
                        holder.rcvBook.setLayoutManager(linearLayoutManager);
                        // below line is to set adapter to our recycler view.
                        holder.rcvBook.setAdapter(bookAdapter);
                    }

                }
            }
            @Override
            public void onFailure(Call<ArrayList<Book>> call, Throwable t) {
                Toast.makeText(context, "Fail to get data", Toast.LENGTH_SHORT).show();
            }
        });




    }


    @Override
    public int getItemCount() {
        return categoryList.size();

    }

    // View Holder Class to handle Recycler View.
    public class CategoryViewHolder extends RecyclerView.ViewHolder{

        // creating variables for our views.
        private TextView tvNameCategory;
        private RecyclerView rcvBook;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameCategory = itemView.findViewById(R.id.tv_name_category);
            rcvBook = itemView.findViewById(R.id.rcv_book_home);

        }
    }

    

}
