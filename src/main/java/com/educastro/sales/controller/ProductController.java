package com.educastro.sales.controller;

import com.educastro.sales.model.dto.ProductDTO;
import com.educastro.sales.model.entities.Product;
import com.educastro.sales.service.ProductService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/products")
@RestController
@CrossOrigin
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @GetMapping
    public  List<Product> findAll(){
        return productService.toListProduct();
    }

    @GetMapping("{id}")
    public Product getProduct(@PathVariable(value = "id") Integer idProduct){
        return productService.findProductById(idProduct);
    }

    @PostMapping
    public Product addProduct(@Validated @RequestBody ProductDTO productDTO){
        return productService.saveProduct(productDTO);
    }

    @PutMapping("{id}")
    public Product updateProduct(@PathVariable(value = "id") Integer idProduct,
                                  @Validated @RequestBody ProductDTO productDTO){
        return productService.updateProduct(idProduct,productDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(value = "id") Integer idProduct){
        productService.deleteProduct(idProduct);
        return ResponseEntity.noContent().build();
    }
}
