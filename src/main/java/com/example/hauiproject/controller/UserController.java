package com.example.hauiproject.controller;

import com.example.hauiproject.model.Book;
import com.example.hauiproject.model.BookOrder;
import com.example.hauiproject.model.Comment;
import com.example.hauiproject.model.Order;
import com.example.hauiproject.service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name= "HomeController",value = "/user")
public class UserController extends HttpServlet {

    PostService postService = new PostService();
    BookService bookService = new BookService();
    CartService cartService = new CartService();
    OrderService orderService = new OrderService();

    CustomerService customerService = new CustomerService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println(action);
        switch (action){
            case "home":
                showHome(req,resp);
                break;
            case "cart":
                showCart(req,resp);
                break;
            case "detail":
                showDetails(req,resp);
                break;
            case"cal":
                calculate(req,resp);
                break;
            case "myorder":
                showMyorder(req,resp);
                break;
            case "orderdetail":
                showOrderDetail(req,resp);
                break;
            case "doComment":
                showComment(req,resp);
                break;
            case"books":
                showBooks(req,resp);
                break;
            case"search":
                search(req,resp);
                break;
            case"sortAsc":
                sortAsc(req,resp);
                break;
            case "sortDesc":
                sortDesc(req,resp);
                break;
        }
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
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("user/prodducts.jsp");
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
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("user/prodducts.jsp");
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
           RequestDispatcher requestDispatcher = req.getRequestDispatcher("user/prodducts.jsp");
           requestDispatcher.forward(req,resp);
    }

    private void showBooks(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException {
        List<Book> books = bookService.findall();
        req.setAttribute("books",books);
        req.getSession().setAttribute("books",books);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("user/prodducts.jsp");
        requestDispatcher.forward(req,resp);
    }

    private void showComment(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        int id = (int) req.getSession().getAttribute("accountId");
        String book = req.getParameter("id");
        String name = customerService.getUsername(String.valueOf(id));
        List<Order> orders = orderService.getOrdersCustomer(String.valueOf(id));
        req.setAttribute("id",book);
        req.setAttribute("orders",orders);
        req.setAttribute("name",name);
        req.setAttribute("number",orders.size());
        String display = "display: block !important;";
        req.setAttribute("display",display);
        RequestDispatcher request = req.getRequestDispatcher("user/account-order.jsp");
        request.forward(req,resp);
    }

    private void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        String id = req.getParameter("id");
        try {
            Order order = orderService.getOrder(id);
            req.setAttribute("order",order);
            req.setAttribute("numbers",order.getBooks().size());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("user/order.jsp");
            requestDispatcher.forward(req,resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void showMyorder(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        int id = (int) req.getSession().getAttribute("accountId");
        String name = customerService.getUsername(String.valueOf(id));
        List<Order> orders = orderService.getOrdersCustomer(String.valueOf(id));
        req.setAttribute("orders",orders);
        req.setAttribute("name",name);
        req.setAttribute("number",orders.size());
        RequestDispatcher request = req.getRequestDispatcher("user/account-order.jsp");
        request.forward(req,resp);
    }

    private void calculate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = (int) req.getSession().getAttribute("accountId");
        try {
            System.out.println(req.getParameter("quantity"));
            List<Book> list =  cartService.getCart(String.valueOf(id));
            List<BookOrder> bookOrders = new ArrayList<BookOrder>();
            for(int i = 0 ; i < list.size() ; i++) {
                long quantity  = Long.valueOf(req.getParameter("quantity"+list.get(i).getId()));
                System.out.println(quantity);
                bookOrders.add(new BookOrder(list.get(i),quantity));
            }
            System.out.println("+++"+Arrays.toString(bookOrders.toArray()));
            double price = orderService.getPrice(bookOrders);
            req.setAttribute("totalmoney",price);
            String ship = req.getParameter("ship");
            req.setAttribute("totals",price+Double.parseDouble(ship));
        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            List<Book> list = cartService.getCart(String.valueOf(id));
            req.setAttribute("numbers",list.size());
            req.setAttribute("books", list);
        }catch (SQLException e ){
            e.printStackTrace();
        }
        RequestDispatcher request = req.getRequestDispatcher("user/cart.jsp");
        request.forward(req,resp);
    }


    public void showDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String id = req.getParameter("id");
        try{
            Book book = bookService.getBook(Integer.parseInt(id));
            List<Comment> comments = postService.getComments(book.getId());
            req.setAttribute("comments",comments);
            req.setAttribute("book", book);
        }catch (SQLException e){
            e.printStackTrace();
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("user/product-detail.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void showCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
             int accountId = (int) req.getSession().getAttribute("accountId");
             try {
                 double price = cartService.getPrice((ArrayList<Book>)cartService.getCart(String.valueOf(accountId)));
                 List<Book> list = cartService.getCart(String.valueOf(accountId));
                 req.setAttribute("numbers",list.size());
                 req.setAttribute("books", list);
             }catch (SQLException e ){
                  e.printStackTrace();
             }
             RequestDispatcher request = req.getRequestDispatcher("user/cart.jsp");
             request.forward(req,resp);
    }



    private void showHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        List<Book> book1 = bookService.getBookByCategory("tn");
        List<Book> book2 = bookService.getBookByCategory("kd");
        List<Book> book3 = bookService.getBookByCategory("tk");
        req.setAttribute("books1",book1);
        req.setAttribute("books2",book2);
        req.setAttribute("books3",book3);
        System.out.println(book1);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("user/home.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String action = req.getParameter("action");
            switch (action) {
                case "addCart":
                    addCart(req, resp);
                    break;
                case "order":
                    order(req, resp);
                    break;
                case "postComment":
                     postComment(req, resp);
                     break;
            }
    }

    private void postComment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = (int) req.getSession().getAttribute("accountId");
        String orderId = req.getParameter("id");
        String comment =  req.getParameter("comment");
        try {
            Order order = orderService.getOrder(orderId);
            List<BookOrder> bookOrders = order.getBooks();
            for (BookOrder bookOrd : bookOrders){
                postService.add(new Comment(String.valueOf(bookOrd.getId()), String.valueOf(id), comment));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        resp.sendRedirect("http://localhost:8080/user?action=cart");
    }

    private void order(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        int id = (int) req.getSession().getAttribute("accountId");
        try {
            List<Book> list =  cartService.getCart(String.valueOf(id));
            List<BookOrder> bookOrders = new ArrayList<BookOrder>();
            for(int i = 0 ; i < list.size() ; i++) {
                long quantity = Long.parseLong(req.getParameter("quantity"+list.get(i).getId()));
                bookOrders.add(new BookOrder(list.get(i),quantity));
            }
            double price = orderService.getPrice(bookOrders);
            String address = req.getParameter("address");
            orderService.add(new Order(bookOrders,String.valueOf(id),address,new Date(System.currentTimeMillis()),price));
        }catch (SQLException e){
            e.printStackTrace();
        }
        resp.sendRedirect("http://localhost:8080/user?action=myorder");
    }

    private void addCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            int accountId = (int)req.getSession().getAttribute("accountId");
            String bookId = req.getParameter("bookId");
            cartService.add(bookId,String.valueOf(accountId) );
            resp.sendRedirect("http://localhost:8080/user?action=home");
    }
}
