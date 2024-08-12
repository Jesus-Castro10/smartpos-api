package com.educastro.sales.service;

import com.educastro.sales.model.dto.ProductDTO;
import com.educastro.sales.exception.ResourceNotFoundException;
import com.educastro.sales.model.entities.Product;
import com.educastro.sales.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.hibernate.mapping.UniqueKey;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author Jesus Castro
 */
@AllArgsConstructor
@Service
public class ProductService implements IProductService{

    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<Product> toListProduct() {
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Product findProductById(Integer idProduct) {
        return productRepository.findById(idProduct).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    @Transactional
    public Product findProductByName(String name) {
        return productRepository.findByNameLike(name);
    }

    @Override
    @Transactional
    public Product saveProduct(ProductDTO productDTO) {
        if (existsByName(productDTO.getName())){
            throw new DuplicateKeyException("Name can't be duplicate");
        }
        Product product = mapper.map(productDTO, Product.class);
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product updateProduct(Integer idProduct, ProductDTO productDTO) {
        Product product = findProductById(idProduct);
        mapper.map(productDTO,product);
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Integer idProduct) {
        Product product = findProductById(idProduct);
        productRepository.delete(product);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }
}
