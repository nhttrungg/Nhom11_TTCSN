package com.example.hauiproject.model;

public class Comment {
    private String book_id;
    private String account_name;
    private String detail;

    public Comment(String id, String detail) {
        this.book_id = id;
        this.detail = detail;
    }

    public Comment(String id, String account_name, String detail) {
        this.book_id = id;
        this.account_name = account_name;
        this.detail = detail;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
