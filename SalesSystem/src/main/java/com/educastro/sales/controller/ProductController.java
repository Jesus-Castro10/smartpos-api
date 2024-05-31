package com.educastro.sales.controller;

import com.educastro.sales.model.Product;
import com.educastro.sales.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String showProducts(ModelMap map){
        List<Product> products = productService.toListProduct();
        map.put("products",products);
        return "products";
    }

    @GetMapping("/addProduct")
    public String showAddProduct(){
        return "addProduct";
    }

    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute(name = "productForm") Product product){
        logger.info("Producto a guardar -> " + product);
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/updateProduct/{id}")
    public String showUpdate(@PathVariable(value = "id") Integer idProduct,ModelMap map){
        Product product = productService.findProductById(idProduct);
        logger.info("Enviar update -> " + product);
        map.put("product",product);
        return "updateProduct";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(@ModelAttribute(name = "product") Product product){
        logger.info("Producto a actualizar -> " + product);
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable(value = "id") Integer idProduct){
        Product product = productService.findProductById(idProduct);
        logger.info("Producto a eliminar -> " + product);
        productService.deleteProduct(product);
        return "redirect:/products";
    }
}
