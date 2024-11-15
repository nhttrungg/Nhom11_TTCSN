package com.example.hauiproject.service;

import com.example.hauiproject.model.BookOrder;
import com.example.hauiproject.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    Connection connection = GetConnect.getConnection();
    public void add(Order order) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `order` (customer_id,date,price,address) values(?,?,?,?)");
        PreparedStatement order_detail = connection.prepareStatement("INSERT INTO order_detail (order_id,book_id,quantity) values(?,?,?)");
        preparedStatement.setString(1, order.getAccount());
        preparedStatement.setString(2, order.getDate().toString());
        preparedStatement.setString(4, order.getAddress());
        preparedStatement.setString(3,String.valueOf(order.getPrice()));
        preparedStatement.executeUpdate();
        for (int i = 0 ; i < order.getBooks().size();i++){
            order_detail.setString(1,String.valueOf(getRecently()));
            order_detail.setString(2,String.valueOf(order.getBooks().get(i).getId()));
            order_detail.setString(3,String.valueOf(order.getBooks().get(i).getQuantity()));
            order_detail.executeUpdate();
        }
        PreparedStatement ps = connection.prepareStatement("DELETE  from cart where id = ? and book_id = ?");

        for (int i = 0 ; i < order.getBooks().size();i++){
            ps.setString(1,order.getAccount());
            ps.setString(2,String.valueOf(order.getBooks().get(i).getId()));
            ps.executeUpdate();
        }
    }
    public int getRecently(){
        try{
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT max(id) from `order`");
            if(rs.next())
                return rs.getInt(1);
        }catch (SQLException e){

        }
        return -1;
    }
    public Order getOrder(String id_order) throws SQLException {
        CustomerService customerService =new CustomerService();
        List<BookOrder> books = new ArrayList<>();
        BookService bookService = new BookService();
        PreparedStatement ps = connection.prepareStatement("SELECT book_id,sum(quantity) as quantity FROM order_detail where order_id = ? group by book_id");
        ps.setString(1, id_order);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            books.add(new BookOrder(bookService.getBook(rs.getInt("book_id")),rs.getLong(2)));
        }
        for (int  i = 0 ; i < books.size();i++){
            books.get(i).setImage(books.get(i).getId()+".png");
        }
        PreparedStatement ps2 = connection.prepareStatement("SELECT * FROM `order` where id = ?");
        ps2.setString(1, id_order);
        ResultSet rs2 = ps2.executeQuery(); rs2.next();
        return new Order (rs2.getInt("id"),books,rs2.getString("address"),rs2.getDate("date"),rs2.getDouble("price"));
    }
    public List<Order> getOrdersCustomer(String id) {
        try {
            PreparedStatement order_detail = connection.prepareStatement("SELECT * from `order` where customer_id = ?");
            order_detail.setString(1,id);
            ResultSet result = order_detail.executeQuery();
            List<Order> orders = new ArrayList<Order>();
            while (result.next()) {
                orders.add(getOrder(result.getString(1)));
            }
            return orders;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public double getPrice(List<BookOrder> boooks){
        double S = 0.0;
        for (int i = 0 ; i < boooks.size() ; i++){
            S += boooks.get(i).getPrice()*boooks.get(i).getQuantity();
        }
        return S;
    }
}
