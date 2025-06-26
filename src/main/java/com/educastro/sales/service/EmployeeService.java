package com.educastro.sales.service;

import com.educastro.sales.model.dto.EmployeeDTO;
import com.educastro.sales.exception.ResourceNotFoundException;
import com.educastro.sales.model.entities.Employee;
import com.educastro.sales.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Jesus Castro
 */
@AllArgsConstructor
@Service
public class EmployeeService implements IEmployeeService{

    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;

    @Override
    public List<Employee> toListEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeById(Integer idEmployee) {
        return employeeRepository.findById(idEmployee).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Employee saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = mapper.map(employeeDTO, Employee.class);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Integer idEmployee, EmployeeDTO employeeDTO) {
        Employee employee = findEmployeeById(idEmployee);
        mapper.map(employeeDTO,employee);
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Integer idEmployee) {
        Employee employee = findEmployeeById(idEmployee);
        employeeRepository.delete(employee);
    }
    
}
