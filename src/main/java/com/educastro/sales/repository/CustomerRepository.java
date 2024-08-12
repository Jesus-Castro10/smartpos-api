package com.educastro.sales.repository;

import com.educastro.sales.model.entities.Customer;
import com.educastro.sales.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 *
 * @author Jesus Castro
 */
public interface CustomerRepository extends JpaRepository<Customer,Integer>{
    Optional<Customer> findByUser(User user);
    boolean existsByEmail(String email);
}
