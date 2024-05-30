package com.educastro.sales.service;

import com.educastro.sales.model.Employee;

import java.util.List;

/**
 *
 * @author Jesus Castro
 */
public interface IEmployeeService {
    public List<Employee> toListEmployee();
    public Employee findEmployeeById(String idSeller);
    public void saveEmployee(Employee seller);
    public void deleteEmployee(Employee seller);
}
