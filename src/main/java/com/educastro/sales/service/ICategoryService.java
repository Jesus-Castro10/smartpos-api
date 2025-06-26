package com.educastro.sales.service;

import com.educastro.sales.model.dto.CategoryDTO;
import com.educastro.sales.model.entities.Category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoryService {
    public Page<Category> findAll(Pageable pageable);
    public Category findById(Integer id);
    public Category save(CategoryDTO categoryDTO);
    public void delete(Integer id);
    public boolean existsByName(String name);
    public Category update(Integer id, CategoryDTO categoryDTO);
}
