package konta.miniproject.service.impl;

import konta.miniproject.dao.impl.BookDAOImpl;
import konta.miniproject.dao.impl.CategoryDAOImpl;
import konta.miniproject.entity.Book;
import konta.miniproject.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    @Override
    public List<Book> findAll() {
        return new BookDAOImpl().findAll();
    }

    @Override
    public Book findById(int bookId) {
        return new BookDAOImpl().findById(bookId);
    }

    @Override
    public boolean add(Book book) {
        return new BookDAOImpl().add(book);
    }

    @Override
    public boolean edit(Book book) {
        return new BookDAOImpl().edit(book);
    }

    @Override
    public boolean delete(int bookId) {
        return new BookDAOImpl().delete(bookId);
    }

    @Override
    public List<Book> findByName(String bookName) {
        return new BookDAOImpl().findByName(bookName);
    }
}
