package com.educastro.sales.service;

import com.educastro.sales.model.dto.ProductDTO;
import com.educastro.sales.exception.ResourceNotFoundException;
import com.educastro.sales.model.entities.Product;
import com.educastro.sales.model.mapper.ProductMapper;
import com.educastro.sales.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jesus Castro
 */
@AllArgsConstructor
@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final ModelMapper mapper;
    private final ProductMapper productMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<Product> findAll(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        if (products.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return products;
    }

    @Override
    @Transactional(readOnly = true)
    public Product findById(Integer id) {
        return productRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public Product findByName(String name) {
        return productRepository.findByNameLike(name).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    @Transactional
    public Product save(ProductDTO productDTO) {
        if (existsByName(productDTO.getName())) {
            throw new DuplicateKeyException("Name can't be duplicate");
        }
        Product product = productMapper.map(productDTO);
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product update(Integer idProduct, ProductDTO productDTO) {
        Product product = findById(idProduct);
        mapper.map(productDTO, product);
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public void delete(Integer idProduct) {
        Product product = findById(idProduct);
        productRepository.delete(product);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }

}
