package com.educastro.sales.service;

import com.educastro.sales.model.dto.CategoryDTO;
import com.educastro.sales.exception.ResourceNotFoundException;
import com.educastro.sales.model.entities.Category;
import com.educastro.sales.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    @Override
    public Page<Category> findAll(Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable);
        if (categories.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return categories;
    }

    @Override
    public Category findById(Integer idCategory) {
        return categoryRepository.findById(idCategory).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Category save(CategoryDTO categoryDTO) {
        Category category = mapper.map(categoryDTO, Category.class);
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Integer idCategory) {
        Category category = findById(idCategory);
        categoryRepository.delete(category);
    }

    @Override
    public boolean existsByName(String name) {
        System.out.println("Checking if category exists by name: " + name);
        return categoryRepository.existsByName(name);
    }

    @Override
    public Category update(Integer id, CategoryDTO categoryDTO) {
        Category existingCategory = findById(id);
        existingCategory.setName(categoryDTO.getName());
        mapper.map(categoryDTO, existingCategory);
        return categoryRepository.save(existingCategory);
    }
}
