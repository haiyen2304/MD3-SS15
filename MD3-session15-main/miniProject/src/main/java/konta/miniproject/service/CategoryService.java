package konta.miniproject.service;

import konta.miniproject.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(int cateId);
    boolean add(Category category);
    boolean edit(Category category);
    boolean delete(int cateId);
    List<Category> findByName(String cateName);
}
