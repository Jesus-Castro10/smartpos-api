package com.educastro.sales.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import com.educastro.sales.exception.ResourceNotFoundException;
import com.educastro.sales.model.entities.Product;
import com.educastro.sales.model.mapper.ProductMapper;
import com.educastro.sales.repository.ProductRepository;

public class ProductServiceTest {

    private ProductRepository productRepository;
    private ModelMapper modelMapper;
    private ProductMapper productMapper;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        modelMapper = new ModelMapper();
        productMapper = mock(ProductMapper.class);

        productService = new ProductService(productRepository, modelMapper, productMapper);
    }

    @Test
    void testFindByIdSuccessfully() {
        Product product = new Product("Keyboard", 50);
        product.setId(1);

        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        Product found = productService.findById(1);

        assertNotNull(found);
        assertEquals("Keyboard", found.getName());
    }

    @Test
    void testFindByIdNotFoundThrowsException() {
        when(productRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.findById(99));
    }
}
