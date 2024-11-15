package com.example.hauiproject.service;

import com.example.hauiproject.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CartService {
    Connection connection = GetConnect.getConnection();
    public void add(String bookId,String id_customer) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Cart (id,book_id) VALUES (?,?)");
            statement.setString(1,id_customer);
            statement.setString(2,bookId);
            statement.executeUpdate();
        }catch (SQLException e){
           e.printStackTrace();
        }
    }
    public List<Book> getCart(String id_customer) throws SQLException {
        List<Book> list = new ArrayList<>();
        BookService bookService = new BookService();
        PreparedStatement statement = connection.prepareStatement("SELECT c.book_id from cart c join book b on b.id = c.book_id where c.id = ?");
        statement.setString(1,id_customer);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            list.add(bookService.getBook(rs.getInt(1)));
        }
        return list;
    }

    public double getPrice(ArrayList<Book> list){
        double price = 0;
        for (Book b : list){
            price += b.getPrice();
        }
        return price;
    }


}
