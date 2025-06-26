package com.educastro.sales.controller;

import com.educastro.sales.model.dto.ProductDTO;
import com.educastro.sales.model.entities.Product;
import com.educastro.sales.service.ProductService;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/api/products")
@RestController
@CrossOrigin
public class ProductController {


    private final ProductService productService;

    @GetMapping
    public  ResponseEntity<Page<Product>> findAll(@PageableDefault(size = 10) Pageable pageable){
        return ResponseEntity.ok(productService.findAll(pageable));
    }

    @GetMapping("{id}")
    public Product getProduct(@PathVariable Integer id){
        return productService.findById(id);
    }

    @PostMapping
    public Product addProduct(@Validated @RequestBody ProductDTO productDTO){
        return productService.save(productDTO);
    }

    @PutMapping("{id}")
    public Product updateProduct(@PathVariable Integer id,
                                  @Validated @RequestBody ProductDTO productDTO){
        return productService.update(id,productDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
