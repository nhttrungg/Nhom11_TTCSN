package com.example.hauiproject.controller;

import com.example.hauiproject.model.Account;
import com.example.hauiproject.model.Book;
import com.example.hauiproject.model.Order;
import com.example.hauiproject.service.BookService;
import com.example.hauiproject.service.CustomerService;
import com.example.hauiproject.service.OrderService;
import com.sun.org.apache.xpath.internal.operations.Or;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name="login",value="/login")
public class BaseController extends HttpServlet {
    CustomerService customerService = new CustomerService();
    OrderService orderService = new OrderService();

    BookService bookService = new BookService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "login":
                showLogin(req, resp);
                break;
                case "register":
                    showRegister(req, resp);
                    break;
            case "showHome":
                showHome(req, resp);
                break;
            case"books":
                showBooks(req,resp);
                break;
            case "search":
                search(req,resp);
                break;
            case"sortDesc":
                sortDesc(req,resp);
                break;
            case"sortAsc":
                sortAsc(req,resp);
                break;
            case"cart":
                showCar(req,resp);

        }
    }

    private void showCar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books= (List<Book>) req.getSession().getAttribute("cart");
        req.setAttribute("books",books);
        RequestDispatcher requestDispatcher=req.getRequestDispatcher("cart.jsp");
        requestDispatcher.forward(req,resp);
    }

    public void sortAsc(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        List<Book> books = (List<Book>) req.getSession().getAttribute("books");
        books.sort((Book a,Book b)->{
            if(a.getPrice() > b.getPrice())
                return -1;
            return 1;
        });
        req.setAttribute("books",books);
        req.getSession().setAttribute("books",books);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("prodducts.jsp");
        requestDispatcher.forward(req,resp);
    }
    public void sortDesc(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        List<Book> books = (List<Book>) req.getSession().getAttribute("books");
        books.sort((Book a,Book b)->{
            if(a.getPrice() > b.getPrice())
                return 1;
            return -1;
        });
        req.setAttribute("books",books);
        req.getSession().setAttribute("books",books);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("prodducts.jsp");
        requestDispatcher.forward(req,resp);
    }
    private void search(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException {
        String name = req.getParameter("search_text");

        String category = req.getParameter("category");
        System.out.println(name);
        System.out.println(category);
        List<Book> books = bookService.search(name,category);
        System.out.println(books);
        req.setAttribute("books",books);
        req.getSession().setAttribute("books",books);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("prodducts.jsp");
        requestDispatcher.forward(req,resp);
    }

    private void showBooks(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException {
        List<Book> books = bookService.findall();
        req.setAttribute("books",books);
        req.getSession().setAttribute("books",books);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("prodducts.jsp");
        requestDispatcher.forward(req,resp);
    }


    public void showLogin(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
        requestDispatcher.forward(req, resp);
    }
    public void showRegister(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("register.jsp");
        requestDispatcher.forward(req,resp);
    }

    public void showHome(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "login":
                login(req, resp);
                break;
                case "register":
                    register(req, resp);
                    break;
        }
    }

    private void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            int i = customerService.checkAccount(username, password);
            System.out.println(i);
            if(i != -1){
                HttpSession session = req.getSession();
                session.setAttribute("accountId",i);
                if(customerService.getRole(String.valueOf(i)))
                    resp.sendRedirect("http://localhost:8080/admin?action=home");
                else
                    resp.sendRedirect("http://localhost:8080/user?action=home");

            }else{
                resp.sendRedirect("http://localhost:8080/login?action=login");
            }
        }catch (SQLException e) {

        }
    }
}
