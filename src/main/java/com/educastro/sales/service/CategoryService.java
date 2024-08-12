package com.educastro.sales.service;

import com.educastro.sales.model.dto.CategoryDTO;
import com.educastro.sales.exception.ResourceNotFoundException;
import com.educastro.sales.model.entities.Category;
import com.educastro.sales.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CategoryService implements ICategoryService{

    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    @Override
    public List<Category> toListCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryById(Integer idCategory) {
        return categoryRepository.findById(idCategory).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Category saveCategory(CategoryDTO categoryDTO) {
        Category category = mapper.map(categoryDTO,Category.class);
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Integer idCategory) {
        Category category = findCategoryById(idCategory);
        categoryRepository.delete(category);
    }

    @Override
    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }
}
