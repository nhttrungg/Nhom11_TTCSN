package com.example.hauiproject.service;

import com.example.hauiproject.model.Account;
import com.example.hauiproject.model.Comment;
import com.example.hauiproject.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostService {
    Connection connection = GetConnect.getConnection();
    CustomerService customerService = new CustomerService();

    public List<Comment> getComments(int bookId) throws SQLException {
        ArrayList<Comment> comments = new ArrayList<Comment>();
        PreparedStatement preparedStatement = connection.prepareStatement("select userId,comment from comment where book_id = ?");
        preparedStatement.setString(1,String.valueOf(bookId));
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
            String account = customerService.getUsername(rs.getString("userId"));
            Comment comment = new Comment(rs.getString(1),account,rs.getString("comment"));
            comments.add(comment);
        }
        return comments;
    }

    public double getReviewCount(int bookId) throws SQLException{
        PreparedStatement ps = connection.prepareStatement("SELECT score from review where book_id = ?", bookId);
        ResultSet rs = ps.executeQuery();
        int S = 0;
        int count = 0;
        while (rs.next()){
            S += rs.getInt("score");
            count++;
        }
        return (double) S/count;
    }
    public void add(Comment comment){
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO comment values (?,?,?)");
            ps.setString(1,comment.getBook_id());
            ps.setString(3,comment.getAccount_name());
            ps.setString(2,comment.getDetail());
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
