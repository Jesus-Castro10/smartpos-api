package com.educastro.sales.controller;

import com.educastro.sales.model.dto.CategoryDTO;
import com.educastro.sales.model.entities.Category;
import com.educastro.sales.service.CategoryService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RequestMapping("/api/categories")
@RestController
@CrossOrigin
public class CategoryRestController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Page<Category>> findAll(@PageableDefault(size = 10) Pageable pageable){
        return ResponseEntity.ok(categoryService.findAll(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Integer id){
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @PostMapping
    public Category create(@Valid @RequestBody CategoryDTO categoryDTO){
        return categoryService.save(categoryDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public Category update(@PathVariable Integer id, @Valid @RequestBody CategoryDTO categoryDTO) {
        return categoryService.update(id, categoryDTO);
    }
}
