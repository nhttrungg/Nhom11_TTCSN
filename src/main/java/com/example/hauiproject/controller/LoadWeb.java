package com.example.hauiproject.controller;

import com.example.hauiproject.model.Book;
import com.example.hauiproject.service.BookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet(name = "load",value = "")
public class LoadWeb extends HttpServlet {
    BookService bookService = new BookService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> book1 = bookService.getBookByCategory("tn");
        List<Book> book2 = bookService.getBookByCategory("kd");
        List<Book> book3 = bookService.getBookByCategory("tk");
        List<Book> cart = new LinkedList<>();
        req.getSession().setAttribute("cart",cart);
        req.setAttribute("books1",book1);
        req.setAttribute("books2",book2);
        req.setAttribute("books3",book3);
        System.out.println(book1);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(req,resp);
    }
}
