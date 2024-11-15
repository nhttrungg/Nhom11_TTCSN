package com.example.hauiproject.model;

import javax.management.relation.Role;

public class Account {
    private String username;
    private String password;
    private Cart cart;
    private String role;

    public Account(String username, String password,String role) {
        this.username = username;
        this.role = role;
        this.password = password;
        cart = new Cart();
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
