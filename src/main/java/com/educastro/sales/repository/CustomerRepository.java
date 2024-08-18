package com.educastro.sales.repository;

import com.educastro.sales.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Jesus Castro
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
