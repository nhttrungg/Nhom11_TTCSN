package com.example.hauiproject.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private List<BookOrder> books;
    private String account;
    private String address;
    private Date date;
    private double price;

    public Order(List<BookOrder> books, String account, String address, Date date, double price) {
        this.books = books;
        this.account = account;
        this.address = address;
        this.date = date;
        this.price = price;
    }

    public Order(int id, List<BookOrder> books, String account, String address, Date date, double price) {
        this.id = id;
        this.books = books;
        this.account = account;
        this.address = address;
        this.date = date;
        this.price = price;
    }

    public Order(int id,List<BookOrder> books, String address, Date date, double price) {
        this.id = id;
        this.books = books;
        this.address = address;
        this.date = date;
        this.price = price;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Order{" +
                "books=" + books +
                ", address='" + address + '\'' +
                ", date=" + date +
                ", price=" + price +
                '}';
    }

    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<BookOrder> getBooks() {
        return books;
    }

    public void setBooks(List<BookOrder> books) {
        this.books = books;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
