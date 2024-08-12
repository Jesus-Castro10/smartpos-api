package com.educastro.sales.service;

import com.educastro.sales.model.dto.ProductDTO;
import com.educastro.sales.model.entities.Product;

import java.util.List;

/**
 *
 * @author Jesus Castro
 */
public interface IProductService{
    public List<Product> toListProduct();
    public Product findProductById(Integer idProduct);
    public Product findProductByName(String name);
    public Product saveProduct(ProductDTO productDTO);
    public Product updateProduct(Integer idProduct, ProductDTO productDTO);
    public void deleteProduct(Integer idProduct);
    public boolean existsByName(String name);
}
