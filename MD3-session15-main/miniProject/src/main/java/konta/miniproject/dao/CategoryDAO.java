package konta.miniproject.dao;

import konta.miniproject.entity.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> findAll();
    Category findById(int categoryId);
    boolean add(Category category);
    boolean edit(Category category);
    boolean delete(int categoryId);
    List<Category> findByName(String categoryName);
}
