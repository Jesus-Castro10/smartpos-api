package com.educastro.sales.controller;

import com.educastro.sales.model.Employee;
import com.educastro.sales.model.dto.EmployeeDTO;
import com.educastro.sales.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    // private static final Logger logger =
    // LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String showCustomers(ModelMap map) {
        List<Employee> employees = employeeService.findAll();
        map.put("employees", employees);
        return "employees";
    }

    @GetMapping("/add")
    public String showAddEmployee() {
        return "addEmployee";
    }

    @PostMapping("/add")
    public String addEmployee(@ModelAttribute(name = "employeeForm") EmployeeDTO employee) {
        employeeService.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/update/{id}")
    public String showUpdate(@PathVariable(value = "id") String idCard, ModelMap map) {
        Employee employee = employeeService.findById(idCard);
        map.put("employee", employee);
        return "/updateEmployee";
    }

    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute(name = "employee") EmployeeDTO employee) {
        employeeService.update(employee);
        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable(value = "id") String idCard) {
        employeeService.delete(idCard);
        return "redirect:/employees";
    }

}
