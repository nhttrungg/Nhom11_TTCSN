package com.example.hauiproject.service;

import com.example.hauiproject.model.Book;

import java.sql.*;
import java.util.*;

public class BookService implements IBookService<Book>{

    Connection connection = null;

    PostService postService = new PostService();

    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projecthaui","root","admin");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
  public Book show(int index){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from book where id = ?");
            preparedStatement.setString(1,String.valueOf(index));
            ResultSet book = preparedStatement.executeQuery();
            if(book.next()){
                Statement statement = connection.createStatement();
                ResultSet author = statement.executeQuery("SELECT name FROM author where id = "+ Integer.parseInt(book.getString(3)));
                if(author.next())
                return new Book(book.getInt(1),book.getString(2),author.getString(1),book.getString(4),book.getDouble(5));
            }
        }catch (Exception e){
        }
        return null;
    }

    @Override
    public void add(Book book) {
        try {
            System.out.println(book);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO book (name,author,category,price) values (?,?,?,?)");
            preparedStatement.setString(1,book.getName());
            PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT id from author where name = ?");
            preparedStatement1.setString(1,book.getAuthor());
            ResultSet id_author = preparedStatement1.executeQuery();
            if(!id_author.next()){
                PreparedStatement authorStatement = connection.prepareStatement("INSERT INTO author (name) values (?)");
                authorStatement.setString(1,book.getAuthor());
                authorStatement.executeUpdate();
            }
            PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT id from author where name = ?");
            preparedStatement2.setString(1,book.getAuthor());
            ResultSet id_author1 = preparedStatement2.executeQuery();
            if(id_author1.next()){
                preparedStatement.setString(2,id_author1.getString("id"));
                preparedStatement.setString(3,book.getCategory());
                preparedStatement.setString(4,String.valueOf(book.getPrice()));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> findall() {
        List<Book> books_list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet books = statement.executeQuery("SELECT b.id from book b join author a on a.id = b.author ");
            while (books.next()){
                books_list.add(getBook(books.getInt("id")));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        for (int  i = 0 ; i < books_list.size();i++){
            books_list.get(i).setImage(books_list.get(i).getId()+".png");
            try {
                books_list.get(i).setReviewscore(postService.getReviewCount(books_list.get(i).getId()));
            }catch (SQLException e){

            }
        }
        return books_list;
    }
    public List<Book> search(String name,String category){
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT id from book where (name like ? or author like ? ) and category like ? ");
            ps.setString(1,"%"+name+"%");
            ps.setString(3,"%"+category+"%");
            ps.setString(2,"%"+name+"%");
            ResultSet rs  = ps.executeQuery();
            List<Book> books = new LinkedList<>();
            while (rs.next()){
                books.add(getBook(rs.getInt(1)));
            }
            return books;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void edit(int index, Book book) {
           try {
               PreparedStatement preparedStatement = connection.prepareStatement("UPDATE book SET name = ?,author = ?,category = ?,price= ? where id = ?");
               preparedStatement.setString(1, book.getName());
               preparedStatement.setString(2, book.getAuthor());
               preparedStatement.setString(3, book.getCategory());
               preparedStatement.setString(4,String.valueOf( book.getPrice()));
               preparedStatement.setString(5, String.valueOf(book.getId()));
               preparedStatement.executeUpdate();
           }catch (SQLException e){

           }
    }

    @Override
    public void delete(int id) {
           try {
               Statement statement = connection.createStatement();
               statement.executeUpdate("DELETE from book where id = " + id);
           }catch (Exception e){

           }
    }

    @Override
    public int findIndexById(int id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT id from book where id ="+ id);
            return rs.getInt(1);
        }catch (Exception e){
        }
        return -1;
    }
    public List<Book> sortDesc(ArrayList<Book> bookList) throws Exception{
        PostService postService = new PostService();
        for(int  i = 0 ;  i < bookList.size();i++){
            for(int j = 0 ; j < bookList.size()-i ; j++){
                if(postService.getReviewCount(bookList.get(j).getId()) < postService.getReviewCount(bookList.get(j+1).getId())){
                   Book temp = bookList.get(j);
                   bookList.set(j,bookList.get(j+1));
                   bookList.set(j+1,temp);
                }
            }
        }
        return (List<Book>) bookList;
    }
    public List<Book> sortAsc(ArrayList<Book> bookList) throws Exception{
        PostService postService = new PostService();
        for(int  i = 0 ;  i < bookList.size();i++){
            for(int j = 0 ; j < bookList.size()-i ; j++){
                if(postService.getReviewCount(bookList.get(j).getId()) < postService.getReviewCount(bookList.get(j+1).getId())){
                    Book temp = bookList.get(j);
                    bookList.set(j,bookList.get(j+1));
                    bookList.set(j+1,temp);
                }
            }
        }
        return (List<Book>) bookList;
    }
    public List<Book> sortDesc_price() throws SQLException{
        List<Book> bookList = new ArrayList<Book>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from book ORDER BY price desc ");
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            bookList.add(new Book(rs.getInt("id"),rs.getString("name"),rs.getString("author"),rs.getString("category"),rs.getDouble("price")));
        }
        return bookList;
    }
    public List<Book> sortAsc_price() throws SQLException{
        List<Book> bookList = new ArrayList<Book>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from book ORDER BY price asc ");
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            bookList.add(new Book(rs.getInt("id"),rs.getString("name"),rs.getString("author"),rs.getString("category"),rs.getDouble("price")));
        }
        return bookList;
    }
    public int recently() throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT max(id)) FROM book");
        if(rs.next())
            return rs.getInt("id");
        else
            return -1;
    }

    public List<Book> getBookByCategory(String category){
        try {
            List<Book> books = new LinkedList<>();
            PreparedStatement ps = connection.prepareStatement("SELECT id from book where category = ?");
            ps.setString(1,category);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                   books.add(getBook(rs.getInt(1)));
            }
            for (int  i = 0 ; i < books.size();i++){
                books.get(i).setImage(books.get(i).getId()+".png");
            }
            return books;
        }catch (SQLException e){
                e.printStackTrace();
        }
        return null;
    }
    public Book getBook(int id) throws SQLException{
        PreparedStatement ps = connection.prepareStatement("SELECT b.id,b.name,a.name,b.category,b.price FROM book b join author a on a.id = b.author where b.id = ?");
        ps.setString(1,String.valueOf(id));
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            String category = new String();
            switch (rs.getString("category")){
                case "mn":
                    category ="SÁCH MẦM NON";
                    break;
                case "tn":
                    category = "SÁCH THIẾU NHI";
                    break;
                case "kn":
                    category = "SÁCH KĨ NĂNG";
                    break;
                case "kd":
                    category = "SÁCH KINH DOANH";
                    break;
                case"mb":
                    category = "SÁCH MẸ VÀ BÉ";
                    break;
                case "vh":
                    category = "SÁCH VĂN HỌC";
                    break;
                case"tk":
                    category = "SÁCH THAM KHẢO";
                    break;
                case"nb":
                    category = "NOTEBOOK";
                    break;
            }
            Book book = new Book(rs.getInt(1),rs.getString(2),rs.getString(3),category,rs.getDouble(5));
            book.setImage(book.getId()+".png");
            return book;
        }
        else
            return null;

    }
    public List<Book> search(String target) throws SQLException{
        List<Book> list = new LinkedList<>();
        PreparedStatement search = connection.prepareStatement("SELECT id from book where author = ? or category = ? or name = ? ");
        search.setString(1,"%"+target+"%");
        search.setString(2,"%"+target+"%");
        search.setString(3,"%"+target+"%");
        ResultSet rs = search.executeQuery();
        while (rs.next()){
            list.add(getBook(rs.getInt("id")));
        }
        return list;
    }
    public int getRecently(){
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT max(id) FROM book");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        }catch(SQLException e){

        }
        return -1;
    }
}
