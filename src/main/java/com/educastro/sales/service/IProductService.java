package com.educastro.sales.service;

import com.educastro.sales.model.Product;
import com.educastro.sales.model.dto.ProductDTO;

import java.util.List;

/**
 *
 * @author Jesus Castro
 */
public interface IProductService {
    public List<Product> findAll();

    public Product findById(Integer id);

    public Product findByName(String name);

    public Product save(ProductDTO product);

    public Product update(Product product);

    public void delete(Integer id);
}
