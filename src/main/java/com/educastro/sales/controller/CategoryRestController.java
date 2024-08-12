package com.educastro.sales.controller;

import com.educastro.sales.model.dto.CategoryDTO;
import com.educastro.sales.model.entities.Category;
import com.educastro.sales.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/categories")
@RestController
@CrossOrigin
public class CategoryRestController {

    private final CategoryService categoryService;

    @GetMapping
    public List<Category> findAll(){
        return categoryService.toListCategories();
    }

    @GetMapping("{id}")
    public Category getCategory(@PathVariable(value = "id") Integer idCategory){
        return categoryService.findCategoryById(idCategory);
    }

    @PostMapping
    public Category create(@RequestBody CategoryDTO categoryDTO){
        return categoryService.saveCategory(categoryDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> remove(@PathVariable(value = "id") Integer idCategory){
        categoryService.deleteCategory(idCategory);
        return ResponseEntity.noContent().build();
    }
}
