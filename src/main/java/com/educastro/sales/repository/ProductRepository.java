package com.educastro.sales.repository;

import com.educastro.sales.model.entities.Product;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Jesus Castro
 */
public interface ProductRepository extends JpaRepository<Product, Integer>{

    Optional<Product> findByNameLike(String name);
    boolean existsByName(String name);
}
