package com.educastro.sales.service;

import com.educastro.sales.model.Employee;
import com.educastro.sales.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Jesus Castro
 */
@Service
public class EmployeeService implements IEmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> toListEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeById(String idEmployee) {
        return employeeRepository.findById(idEmployee).orElse(null);
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }
    
}
