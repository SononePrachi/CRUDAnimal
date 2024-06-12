package com.animal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.animal.dao.CategoryDao;
import com.animal.entities.Category;

@Service
public class CategoryService {
	@Autowired
    private CategoryDao categoryDao;

    public List<Category> getAllCategories() {
        return categoryDao.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryDao.findById(id);
    }
}
