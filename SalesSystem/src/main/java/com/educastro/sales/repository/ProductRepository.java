package com.educastro.sales.repository;

import com.educastro.sales.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Jesus Castro
 */
public interface ProductRepository extends JpaRepository<Product, Integer>{
    @Query("SELECT p FROM Product p WHERE p.name = :name")
    Product findByName(@Param("name") String name);
}
