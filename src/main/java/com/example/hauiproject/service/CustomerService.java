package com.example.hauiproject.service;

import com.example.hauiproject.model.Account;
import com.example.hauiproject.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerService {
          Connection connection = GetConnect.getConnection();
          public void add(Account account, Customer customer) throws SQLException {
              PreparedStatement customer_connect = connection.prepareStatement("INSERT INTO customer ( name,phonenumber ) VALUES(?,?)");
              PreparedStatement account_connect = connection.prepareStatement("INSERT INTO account ( username,password,role ) VALUES(?,?,?)");
              customer_connect.setString(1,customer.getName());
              customer_connect.setString(2,customer.getPhonenumber());
              customer_connect.execute();
              account_connect.setString(1,account.getUsername());
              account_connect.setString(2,account.getPassword());
              account_connect.setString(3,"user");
              account_connect.execute();
          }
          public int checkAccount(String username, String password) throws SQLException {
              PreparedStatement st = connection.prepareStatement("SELECT * from account where username = ? and password = ?");
              st.setString(1,username);
              st.setString(2,password);
              ResultSet rs = st.executeQuery();
              if(rs.next()) {
                  return rs.getInt("id");
              }else
                  return -1;
          }
          public void addAdress(String address,String customer_id) throws SQLException {
              PreparedStatement st = connection.prepareStatement("INSERT INTO adresses (id_customer,address) values (?,?)");
              st.setString(1,customer_id);
              st.setString(2,address);
              st.executeUpdate();
          }
          public void deleteAddress(String id_address) throws SQLException {
              PreparedStatement st = connection.prepareStatement("delete from address where id = ?");
              st.setString(1,id_address);
              st.executeUpdate();
          }
          public Customer getCustomer(String id) {
              try {
                  PreparedStatement st = connection.prepareStatement("SELECT * from customer where id = ?");
                  st.setString(1,id);
                  ResultSet rs = st.executeQuery(); rs.next();
                  return new Customer(rs.getInt("id"),rs.getString("name"),rs.getString(3));
              }catch (SQLException e){

              }
              return null;
          }

          public String getUsername(String id){
              try {
                  PreparedStatement st = connection.prepareStatement("SELECT * from account where id = ?");
                  st.setString(1,id);
                  ResultSet rs = st.executeQuery(); rs.next();
                  return  rs.getString("username");
              }catch (SQLException e){

              }
              return null;
          }

          public boolean getRole(String id){
              try {
                  PreparedStatement preparedStatement = connection.prepareStatement("SELECT role from account where id = ?");
                  preparedStatement.setString(1,id);
                  ResultSet rs = preparedStatement.executeQuery();
                  if(rs.next()){
                      if(rs.getString(1).equals("admin"))
                          return true;
                      else
                          return false;
                  }
              }catch (SQLException e){

              }
              return false;
          }

}
