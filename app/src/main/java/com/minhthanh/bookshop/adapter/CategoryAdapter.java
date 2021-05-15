package com.minhthanh.bookshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.minhthanh.bookshop.R;
import com.minhthanh.bookshop.api.GetBookApi;

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

    public CategoryAdapter(Context context, ArrayList<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }



    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.home_item_category,parent,false);
        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(view);


        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

        // Set the data to textview from our modal class.
        Category  category = categoryList.get(position);

        holder.tvNameCategory.setText(category.getName_category());
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL,false);
//        holder.rcvBook_home.setLayoutManager(linearLayoutManager);

        // below line we are running a loop to add data to our adapter class.
        bookList = getBookList();
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


    //getbookList

    //https://demo8468432.mockable.io/GetBook
    private ArrayList<Book> getBookList(){
        bookList = new ArrayList<>();
        // on below line we are creating a retrofit
        // builder and passing our base url
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://demo8468432.mockable.io/")
                // on below line we are calling add
                // Converter factory as Gson converter factory.
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        GetBookApi getBookApi = retrofit.create(GetBookApi.class);

        // on below line we are calling a method to get all the courses from API.
        Call<ArrayList<Book>> call = getBookApi.getBook();

        // on below line we are calling method to enqueue and calling
        // all the data from array list.
        call.enqueue(new Callback<ArrayList<Book>>() {

            @Override
            public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response) {
                // inside on response method we are checking
                // if the response is success or not.
                if (response.isSuccessful()) {
                    // on successful we are hiding our progressbar.

                    // below line is to add our data from api to our array list.
                    bookList = response.body();

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Book>> call, Throwable t) {

                // in the method of on failure we are displaying a
                // toast message for fail to get data.
                Toast.makeText(context, "Fail to get data", Toast.LENGTH_SHORT).show();

            }
        });
        return bookList;
    }
}
