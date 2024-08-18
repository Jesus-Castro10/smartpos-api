package com.educastro.sales.controller;

import com.educastro.sales.model.Product;
import com.educastro.sales.model.dto.ProductDTO;
import com.educastro.sales.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String showProducts(ModelMap map) {
        List<Product> products = productService.findAll();
        map.put("products", products);
        return "products";
    }

    @GetMapping("/add")
    public String showAddProduct() {
        return "addProduct";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute(name = "productForm") ProductDTO product) {
        Product pg = productService.save(product);
        System.out.println("produto guardado -> " + pg);
        return "redirect:/products";
    }

    @GetMapping("/update/{id}")
    public String showUpdate(@PathVariable Integer id, ModelMap map) {
        Product product = productService.findById(id);
        map.put("product", product);
        return "updateProduct";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute(name = "product") Product product) {
        productService.update(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        productService.delete(id);
        return "redirect:/products";
    }
}
