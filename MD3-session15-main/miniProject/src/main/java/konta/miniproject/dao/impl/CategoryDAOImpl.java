package konta.miniproject.dao.impl;

import konta.miniproject.dao.CategoryDAO;
import konta.miniproject.db.DatabaseUtility;
import konta.miniproject.entity.Book;
import konta.miniproject.entity.Category;
import konta.miniproject.service.impl.BookServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    @Override
    public List<Category> findAll() {
        List<Category> list = new ArrayList<Category>();

        Connection con = null;
        PreparedStatement pstmt;
        ResultSet rs;

        con = DatabaseUtility.getConnection();
        try {
            pstmt = con.prepareCall("call get_all_categories()");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Category a = new Category();
                a.setId(rs.getInt("id"));
                a.setName(rs.getString("name"));
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
    public Category findById(int categoryId) {
        Category e = null;

        Connection con;
        CallableStatement cstmt;
        ResultSet rs;

        con = DatabaseUtility.getConnection();
        try {

            cstmt = con.prepareCall("{call get_category_by_id(?)}");
            cstmt.setInt(1, categoryId);
            rs = cstmt.executeQuery();
            if(rs.next()){
                e = new Category();
                e.setId(rs.getInt("id"));
                e.setName(rs.getString("name"));
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
    public boolean add(Category category) {
        boolean bl = false;

        Connection con;
        CallableStatement cstmt;

        con = DatabaseUtility.getConnection();
        try {
            cstmt = con.prepareCall("{call insert_category(?,?)}");
            cstmt.setString(1, category.getName());
            cstmt.setBoolean(2, category.getStatus());
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
    public boolean edit(Category category) {
        boolean bl = false;

        Connection con;
        CallableStatement cstmt;

        con = DatabaseUtility.getConnection();
        try {
            cstmt = con.prepareCall("{call update_category(?,?,?)}");
            cstmt.setInt(1, category.getId());
            cstmt.setString(2, category.getName());
            cstmt.setBoolean(3, category.getStatus());
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
    public boolean delete(int categoryId) {
        boolean bl = false;
        Connection con;
        CallableStatement cstmt;
        con = DatabaseUtility.getConnection();

        //check if category has books
        boolean bookExist = false;
        BookServiceImpl bookService = new BookServiceImpl();
        List<Book> books = bookService.findAll();
        for (Book book : books) {
            if (book.getCategoryId() == categoryId) {
                bookExist = true;
                break;
            }
        }
        if (bookExist) {
            System.out.println("Co Books trong lop. Ko xoa dc");
            return false;
        }
        //end check

        try {
            cstmt =  con.prepareCall("{call delete_category(?)}");
            cstmt.setInt(1, categoryId);
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
    public List<Category> findByName(String categoryName) {
        List<Category> list = new ArrayList<>();

        Connection con;
        CallableStatement cstmt;
        ResultSet rs;

        con = DatabaseUtility.getConnection();
        try {
            cstmt = con.prepareCall("{call get_category_by_name(?)}");
            if(categoryName.equals("null"))
                categoryName = "";
            cstmt.setString(1, categoryName);
            rs = cstmt.executeQuery();
            while(rs.next()){
                Category s = new Category();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
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
