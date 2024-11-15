package com.example.hauiproject.model;

import java.util.ArrayList;

public class Post {
    private int bookId;
    private ArrayList<Comment> comments;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}
