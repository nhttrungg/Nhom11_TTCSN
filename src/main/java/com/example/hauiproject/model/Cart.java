package com.example.hauiproject.model;

import java.util.ArrayList;

public class Cart {
    private int id;

    private ArrayList<Book> books = new ArrayList<>();

    public Cart(int id, ArrayList<Book> books) {
        this.id = id;
        this.books = books;
    }

    public Cart() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
}
