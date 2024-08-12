package com.educastro.sales.service;

import com.educastro.sales.model.dto.CategoryDTO;
import com.educastro.sales.model.entities.Category;

import java.util.List;

public interface ICategoryService {
    public List<Category> toListCategories();
    public Category findCategoryById(Integer idCategory);
    public Category saveCategory(CategoryDTO categoryDTO);
    public void deleteCategory(Integer idCategory);
    public boolean existsByName(String name);
}
