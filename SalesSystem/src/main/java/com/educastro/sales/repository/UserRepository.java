package com.educastro.sales.repository;

import com.educastro.sales.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Employee,String> {
    Employee findByUsername(String username);
}
