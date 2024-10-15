package konta.miniproject.dao;

import konta.miniproject.entity.Book;
import konta.miniproject.entity.Category;

import java.util.List;

public interface BookDAO {
    List<Book> findAll();
    Book findById(int bookId);
    boolean add(Book book);
    boolean edit(Book book);
    boolean delete(int bookId);
    List<Book> findByName(String bookName);
}
