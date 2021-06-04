package com.minhthanh.bookshop.event;


import model.Book;

public interface IonItemClickBook_home {
    void onName(String namebook);
    void onImage(Book book );
    void onNamCat(String name);
}
