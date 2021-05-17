package com.minhthanh.bookshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.minhthanh.bookshop.R;
import com.minhthanh.bookshop.api.GetBookApi;
import com.minhthanh.bookshop.event.IonItemClickBook_home;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import model.Book;
import model.Category;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder>{

    // creating a variable for our array list and context.
    private Context context;
    private ArrayList<Book> bookList;

    public BookAdapter(Context context, ArrayList<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public BookAdapter.BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.home_item_book,parent,false);
        BookAdapter.BookViewHolder bookViewHolder = new BookAdapter.BookViewHolder(view);

        return bookViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.BookViewHolder holder, int position) {

        // Set the data to textview from our modal class.
        Book  book = bookList.get(position);

        holder.tvNameBook.setText(book.getName_book());
        Picasso.get().load(book.getImage_book()).into(holder.imgBook);

    }

    @Override
    public int getItemCount() {
        return bookList.size();

    }

    // View Holder Class to handle Recycler View.
    public class BookViewHolder extends RecyclerView.ViewHolder{

        // creating variables for our views.
        private TextView tvNameBook;
        private ImageView imgBook;
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            imgBook = itemView.findViewById(R.id.img_home_item_book);
            tvNameBook = itemView.findViewById(R.id.tv_home_name_book);

        }
    }

    //getbookList

    //https://demo8468432.mockable.io/GetBook
    public ArrayList<Book> getBookList(){
        bookList = new ArrayList<>();

        // on below line we are creating a retrofit builder and passing our base url
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://demo8468432.mockable.io/")
                // on below line we are calling add Converter factory as Gson converter factory.
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        GetBookApi getBookApi = retrofit.create(GetBookApi.class);
        // on below line we are calling a method to get all the courses from API.
        Call<ArrayList<Book>> call = getBookApi.getBook();
        // on below line we are calling method to enqueue and calling all the data from array list.
        call.enqueue(new Callback<ArrayList<Book>>() {
            @Override
            public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response) {
                // inside on response method we are checking if the response is success or not.
                if (response.isSuccessful()) {
                    // below line is to add our data from api to our array list.

                    bookList = response.body();

                }
            }
            @Override
            public void onFailure(Call<ArrayList<Book>> call, Throwable t) {
                // in the method of on failure we are displaying a  toast message for fail to get data.
                Toast.makeText(context, "Fail to get data", Toast.LENGTH_SHORT).show();
            }
        });
        return bookList;
    }


}
