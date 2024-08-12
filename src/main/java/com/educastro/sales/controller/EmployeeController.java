package com.educastro.sales.controller;

import com.educastro.sales.model.dto.EmployeeDTO;
import com.educastro.sales.model.entities.Employee;
import com.educastro.sales.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/employees")
@RestController
@CrossOrigin
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final EmployeeService employeeService;

    @GetMapping
    public List<Employee> findAll() {
        return employeeService.toListEmployee();
    }

    @GetMapping("{id}")
    public Employee findEmployee(@PathVariable(value = "id") String username){
        return employeeService.findEmployeeById(username);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.saveEmployee(employeeDTO);
    }

    @PutMapping
    public Employee updateEmployee(@PathVariable(value = "id") String username,
                                   @RequestBody EmployeeDTO employeeDTO){
        return employeeService.updateEmployee(username,employeeDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable(value = "id") String username){
        employeeService.deleteEmployee(username);
        return ResponseEntity.noContent().build();
    }
}
