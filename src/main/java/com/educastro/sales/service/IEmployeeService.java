package com.educastro.sales.service;

import com.educastro.sales.model.Employee;
import com.educastro.sales.model.User;
import com.educastro.sales.model.dto.EmployeeDTO;

import java.util.List;

/**
 *
 * @author Jesus Castro
 */
public interface IEmployeeService {
    public List<Employee> findAll();

    public Employee findById(String id);

    public Employee findByUser(User user);

    public Employee save(EmployeeDTO employee);

    public Employee update(EmployeeDTO employee);

    public void delete(String id);
}
