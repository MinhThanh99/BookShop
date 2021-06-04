package com.minhthanh.bookshop.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.minhthanh.bookshop.R;
import com.minhthanh.bookshop.event.IonItemClickBook_home;
import com.minhthanh.bookshop.fragment.SignInFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import model.Book;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder>{

    // creating a variable for our array list and context.
    private Context context;
    private ArrayList<Book> bookList;

    IonItemClickBook_home ionItemClickBook_home;
//    OnItemClickListener1 onItemClickListener1;



    public void setIonItemClickBook_home(IonItemClickBook_home ionItemClickBook_home){
        this.ionItemClickBook_home = ionItemClickBook_home;
    }

//    public void setOnItemClickListener(OnItemClickListener1 onItemClickListener){
//        this.onItemClickListener1 = onItemClickListener;
//    }

    public BookAdapter(Context context, ArrayList<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.home_item_book,parent,false);
        BookViewHolder bookViewHolder = new BookViewHolder(view);

        return bookViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {

        // Set the data to textview from our modal class.
        Book  book = bookList.get(position);

        holder.tvNameBook.setText(book.getName_book());
        Picasso.get().load(book.getImage_book()).into(holder.imgBook);

        //set sự kiện click
        holder.cardViewBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                SignInFragment.BookDetailFragment bookDetailFragment = new SignInFragment.BookDetailFragment();

                Bundle bundle = new Bundle();

                bookDetailFragment.setArguments(bundle);
                fragmentTransaction.addToBackStack("ActivityMain");
                fragmentTransaction.replace(R.id.container, bookDetailFragment);
                fragmentTransaction.commit();
            }
        });

//        holder.tvNameBook.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                ionItemClickBook_home.onName(book.getName_book());
//                {
//                    Toast.makeText(context,book.getName_book(),Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
//
//        holder.imgBook.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ionItemClickBook_home.onImage(book);
//            }
//        });


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
        private CardView cardViewBook;
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            imgBook = itemView.findViewById(R.id.img_home_item_book);
            tvNameBook = itemView.findViewById(R.id.tv_home_name_book);
            cardViewBook = itemView.findViewById(R.id.item_book);

        }
    }

    public interface OnItemClickListener1{
        void onItemClick(Book book);
    }




}
