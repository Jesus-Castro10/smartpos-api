package com.educastro.sales.service;

import com.educastro.sales.exceptions.ResourceNotFoundException;
import com.educastro.sales.model.Product;
import com.educastro.sales.model.dto.ProductDTO;
import com.educastro.sales.repository.ProductRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Jesus Castro
 */
@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Product save(ProductDTO productDTO) {
        Product product = mapper.map(productDTO, Product.class);
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Integer id) {
        Product product = findById(id);
        productRepository.delete(product);
    }

}
