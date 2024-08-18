package com.educastro.sales.service;

import com.educastro.sales.exceptions.ResourceNotFoundException;
import com.educastro.sales.model.Employee;
import com.educastro.sales.model.User;
import com.educastro.sales.model.dto.EmployeeDTO;
import com.educastro.sales.model.dto.UserDTO;
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
public class EmployeeService implements IEmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserService userService;
    private final ModelMapper mapper;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(String id) {
        return employeeRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Employee save(EmployeeDTO employeeDTO) {
        Employee employee = mapper.map(employeeDTO, Employee.class);
        UserDTO userDTO = employeeDTO.getUserDTO();
        userDTO.setCashier(true);
        User user = userService.create(userDTO);
        employee.setUser(user);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee findByUser(User user) {
        return employeeRepository.findByUser(user).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void delete(String id) {
        Employee employee = findById(id);
        employeeRepository.delete(employee);
    }

    @Override
    public Employee update(EmployeeDTO employeeDTO) {
        Employee employee = findById(employeeDTO.getIdCard());
        mapper.map(employeeDTO, employee);
        return employeeRepository.save(employee);
    }

}
