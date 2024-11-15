package com.example.hauiproject.model;

public class BookOrder extends Book{
    private long quantity;

    public BookOrder(int id, String name, String author, String category, double price,long quantity) {
        super(id, name, author, category, price);
        this.quantity = quantity;
    }

    public BookOrder(Book book,long quantity) {
        super(book.getId(), book.getName(), book.getAuthor(), book.getCategory(),book.getPrice());
        this.quantity = quantity;
    }
    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
