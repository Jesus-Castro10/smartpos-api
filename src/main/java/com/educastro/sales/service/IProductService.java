package com.educastro.sales.service;

import com.educastro.sales.model.dto.ProductDTO;
import com.educastro.sales.model.entities.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Jesus Castro
 */
public interface IProductService{
    public Page<Product> findAll(Pageable pageable);
    public Product findById(Integer idProduct);
    public Product findByName(String name);
    public Product save(ProductDTO productDTO);
    public Product update(Integer idProduct, ProductDTO productDTO);
    public void delete(Integer idProduct);
    public boolean existsByName(String name);
}
