package com.bryan.backend.service;

import com.bryan.backend.model.Category;
import com.bryan.backend.model.Note;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    Category saveCategory(Category category);
    Category updateCategory(Long id, Category category);
    void deleteCategory(Long id);
}
