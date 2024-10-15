package konta.miniproject.dao.impl;

import konta.miniproject.dao.BookDAO;
import konta.miniproject.db.DatabaseUtility;
import konta.miniproject.entity.Book;
import konta.miniproject.entity.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    @Override
    public List<Book> findAll() {
        List<Book> list = new ArrayList<Book>();

        Connection con = null;
        PreparedStatement pstmt;
        ResultSet rs;

        con = DatabaseUtility.getConnection();
        try {
            pstmt = con.prepareCall("call get_all_book()");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Book a = new Book();
                a.setId(rs.getInt("id"));
                a.setCategoryId(rs.getInt("category_id"));
                a.setName(rs.getString("name"));
                a.setPrice(rs.getDouble("price"));
                a.setStock(rs.getInt("stock"));
                a.setTotalPages(rs.getInt("totalPages"));
                a.setYearCreated(rs.getInt("yearCreated"));
                a.setAuthor(rs.getString("author"));
                a.setBookImage(rs.getString("book_image"));
                a.setStatus(rs.getBoolean("status"));

                list.add(a);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseUtility.closeConnection(con);
        }
        return list;
    }

    @Override
    public Book findById(int bookId) {
        Book e = null;

        Connection con;
        CallableStatement cstmt;
        ResultSet rs;

        con = DatabaseUtility.getConnection();
        try {

            cstmt = con.prepareCall("{call get_book_by_id(?)}");
            cstmt.setInt(1, bookId);
            rs = cstmt.executeQuery();
            if(rs.next()){
                e = new Book();
                e.setId(rs.getInt("id"));
                e.setCategoryId(rs.getInt("category_id"));
                e.setName(rs.getString("name"));
                e.setPrice(rs.getDouble("price"));
                e.setStock(rs.getInt("stock"));
                e.setTotalPages(rs.getInt("totalPages"));
                e.setYearCreated(rs.getInt("yearCreated"));
                e.setAuthor(rs.getString("author"));
                e.setBookImage(rs.getString("book_image"));
                e.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e1) {
            throw new RuntimeException(e1);
        }finally {
            DatabaseUtility.closeConnection(con);
        }

        return e;
    }

    @Override
    public boolean add(Book book) {
        boolean bl = false;

        Connection con;
        CallableStatement cstmt;

        con = DatabaseUtility.getConnection();
        try {
            cstmt = con.prepareCall("{call insert_book(?,?,?,?,?,?,?,?,?)}");
            cstmt.setInt(1, book.getCategoryId());
            cstmt.setString(2, book.getName());
            cstmt.setDouble(3, book.getPrice());
            cstmt.setInt(4, book.getStock());
            cstmt.setInt(5, book.getTotalPages());
            cstmt.setInt(6, book.getYearCreated());
            cstmt.setString(7, book.getAuthor());
            cstmt.setString(8, book.getBookImage());
            cstmt.setBoolean(9, book.getStatus());
            int i = cstmt.executeUpdate();
            if(i>0)
                bl = true;
        } catch (SQLException e1) {
            throw new RuntimeException(e1);
        }finally {
            DatabaseUtility.closeConnection(con);
        }

        return bl;
    }

    @Override
    public boolean edit(Book book) {
        boolean bl = false;

        Connection con;
        CallableStatement cstmt;

        con = DatabaseUtility.getConnection();
        try {
            cstmt = con.prepareCall("{call update_book(?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setInt(1, book.getId());
            cstmt.setInt(2, book.getCategoryId());
            cstmt.setString(3, book.getName());
            cstmt.setDouble(4, book.getPrice());
            cstmt.setInt(5, book.getStock());
            cstmt.setInt(6, book.getTotalPages());
            cstmt.setInt(7, book.getYearCreated());
            cstmt.setString(8, book.getAuthor());
            cstmt.setString(9, book.getBookImage());
            cstmt.setBoolean(10, book.getStatus());
            int i = cstmt.executeUpdate();
            if(i>0)
                bl = true;
        } catch (SQLException e1) {
            throw new RuntimeException(e1);
        }finally {
            DatabaseUtility.closeConnection(con);
        }

        return bl;
    }

    @Override
    public boolean delete(int bookId) {
        boolean bl = false;
        Connection con;
        CallableStatement cstmt;
        con = DatabaseUtility.getConnection();

        try {
            cstmt =  con.prepareCall("{call delete_book(?)}");
            cstmt.setInt(1, bookId);
            int i = cstmt.executeUpdate();
            if (i>0){
                bl = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DatabaseUtility.closeConnection(con);
        }
        return bl;
    }

    @Override
    public List<Book> findByName(String bookName) {
        List<Book> list = new ArrayList<>();

        Connection con;
        CallableStatement cstmt;
        ResultSet rs;

        con = DatabaseUtility.getConnection();
        try {
            cstmt = con.prepareCall("{call get_book_by_name(?)}");
            if(bookName.equals("null"))
                bookName = "";
            cstmt.setString(1, bookName);
            rs = cstmt.executeQuery();
            while(rs.next()){
                Book s = new Book();
                s.setId(rs.getInt("id"));
                s.setCategoryId(rs.getInt("category_id"));
                s.setName(rs.getString("name"));
                s.setPrice(rs.getDouble("price"));
                s.setStock(rs.getInt("stock"));
                s.setTotalPages(rs.getInt("totalPages"));
                s.setYearCreated(rs.getInt("yearCreated"));
                s.setAuthor(rs.getString("author"));
                s.setBookImage(rs.getString("book_image"));
                s.setStatus(rs.getBoolean("status"));
                list.add(s);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DatabaseUtility.closeConnection(con);
        }

        return list;
    }
}
