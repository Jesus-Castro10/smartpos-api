package com.educastro.sales.service;

import com.educastro.sales.model.Product;

import java.util.List;

/**
 *
 * @author Jesus Castro
 */
public interface IProductService{
    public List<Product> toListProduct();
    public Product findProductById(Integer idProduct);
    public Product findProductByName(String name);
    public void saveProduct(Product product);
    public void deleteProduct(Product product);
}
