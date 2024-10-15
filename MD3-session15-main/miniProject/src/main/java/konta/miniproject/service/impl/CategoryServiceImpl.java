package konta.miniproject.service.impl;

import konta.miniproject.dao.impl.CategoryDAOImpl;
import konta.miniproject.entity.Category;
import konta.miniproject.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    @Override
    public List<Category> findAll() {
        return new CategoryDAOImpl().findAll();
    }

    @Override
    public Category findById(int cateId) {
        return new CategoryDAOImpl().findById(cateId);
    }

    @Override
    public boolean add(Category category) {
        return new CategoryDAOImpl().add(category);
    }

    @Override
    public boolean edit(Category category) {
        return new CategoryDAOImpl().edit(category);
    }

    @Override
    public boolean delete(int cateId) {
        return new CategoryDAOImpl().delete(cateId);
    }

    @Override
    public List<Category> findByName(String cateName) {
        return new CategoryDAOImpl().findByName(cateName);
    }
}
