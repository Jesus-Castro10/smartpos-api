package com.educastro.sales.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import com.educastro.sales.exception.ResourceNotFoundException;
import com.educastro.sales.model.dto.ProductDTO;
import com.educastro.sales.model.entities.Product;
import com.educastro.sales.model.mapper.ProductMapper;
import com.educastro.sales.repository.ProductRepository;
import org.springframework.dao.DuplicateKeyException;

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

    
    @Test
    void testFindByNameSuccessfully() {
        Product product = new Product("Keyboard", 50);
        product.setId(1);

        when(productRepository.findByNameLike("keyboard")).thenReturn(Optional.of(product));

        Product found = productService.findByName("keyboard");

        assertNotNull(found);
        assertEquals("Keyboard", found.getName());
    }

    @Test
    void testFindByNameNotFoundThrowsException() {
        when(productRepository.findByNameLike("Mouse")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.findByName("Mouse"));
    }


    @Test
    void testSaveSuccessfully() {
        ProductDTO dto = new ProductDTO();
        Product product = new Product("keyboard", 30);
        product.setId(1);

        when(productRepository.existsByName("keyboard")).thenReturn(false);
        when(productMapper.map(dto)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.save(dto);

        assertNotNull(result);
        assertEquals("keyboard", result.getName());
        assertEquals(30, result.getPrice());
    }

    @Test
    void testSaveDuplicateNameThrowsException() {
        ProductDTO dto = new ProductDTO();
        dto.setName("keyboard");

        when(productRepository.existsByName("keyboard")).thenReturn(true);

        assertThrows(DuplicateKeyException.class, () -> productService.save(dto));
    }


    @Test
    void testDeleteSuccessfully() {
        Product product = new Product("Mouse", 25);
        product.setId(2);

        when(productRepository.findById(2)).thenReturn(Optional.of(product));

        productService.delete(2);

        verify(productRepository).delete(product);
    }

    @Test
    void testDeleteNotFoundThrowsException() {
        when(productRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.delete(99));
    }

}
