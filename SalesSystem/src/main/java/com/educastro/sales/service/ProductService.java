package com.educastro.sales.service;

import com.educastro.sales.model.Product;
import com.educastro.sales.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Jesus Castro
 */
@Service
public class ProductService implements IProductService{

    @Autowired
    private ProductRepository productRepository;
    
    @Override
    public List<Product> toListProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(Integer idProduct) {
        return productRepository.findById(idProduct).orElse(null);
    }

    @Override
    public Product findProductByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }
}
