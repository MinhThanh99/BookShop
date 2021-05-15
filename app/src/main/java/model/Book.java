package model;

public class Book {
    private String id_book;
    private String name_book;
    private String price_book;
    private String author_book;
    private String publishing_book;
    private String page_number_book;
    private String size_book;
    private String date_book;
    private String describe_book;
    private String amount_book;
    private String image_book;
    private String id_category;

    public Book(String id_book, String name_book, String price_book, String author_book, String publishing_book, String page_number_book, String size_book, String date_book, String describe_book, String amount_book, String image_book, String id_category) {
        this.id_book = id_book;
        this.name_book = name_book;
        this.price_book = price_book;
        this.author_book = author_book;
        this.publishing_book = publishing_book;
        this.page_number_book = page_number_book;
        this.size_book = size_book;
        this.date_book = date_book;
        this.describe_book = describe_book;
        this.amount_book = amount_book;
        this.image_book = image_book;
        this.id_category = id_category;
    }

    public String getId_book() {
        return id_book;
    }

    public void setId_book(String id_book) {
        this.id_book = id_book;
    }

    public String getName_book() {
        return name_book;
    }

    public void setName_book(String name_book) {
        this.name_book = name_book;
    }

    public String getPrice_book() {
        return price_book;
    }

    public void setPrice_book(String price_book) {
        this.price_book = price_book;
    }

    public String getAuthor_book() {
        return author_book;
    }

    public void setAuthor_book(String author_book) {
        this.author_book = author_book;
    }

    public String getPublishing_book() {
        return publishing_book;
    }

    public void setPublishing_book(String publishing_book) {
        this.publishing_book = publishing_book;
    }

    public String getPage_number_book() {
        return page_number_book;
    }

    public void setPage_number_book(String page_number_book) {
        this.page_number_book = page_number_book;
    }

    public String getSize_book() {
        return size_book;
    }

    public void setSize_book(String size_book) {
        this.size_book = size_book;
    }

    public String getDate_book() {
        return date_book;
    }

    public void setDate_book(String date_book) {
        this.date_book = date_book;
    }

    public String getDescribe_book() {
        return describe_book;
    }

    public void setDescribe_book(String describe_book) {
        this.describe_book = describe_book;
    }

    public String getAmount_book() {
        return amount_book;
    }

    public void setAmount_book(String amount_book) {
        this.amount_book = amount_book;
    }

    public String getImage_book() {
        return image_book;
    }

    public void setImage_book(String image_book) {
        this.image_book = image_book;
    }

    public String getId_category() {
        return id_category;
    }

    public void setId_category(String id_category) {
        this.id_category = id_category;
    }
}
