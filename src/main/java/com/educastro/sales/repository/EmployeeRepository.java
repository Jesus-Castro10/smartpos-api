package com.educastro.sales.repository;

import com.educastro.sales.model.Employee;
import com.educastro.sales.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Jesus Castro
 */
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    Optional<Employee> findByUser(User user);

}
