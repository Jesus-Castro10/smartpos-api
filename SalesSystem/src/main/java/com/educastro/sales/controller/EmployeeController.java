package com.educastro.sales.controller;

import com.educastro.sales.model.Employee;
import com.educastro.sales.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Random;

@Controller
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public String showCustomers(ModelMap map) {
        List<Employee> employees = employeeService.toListEmployee();
        map.put("employees", employees);
        return "employees";
    }

    @GetMapping("/addEmployee")
    public String showAddEmployee() {
        return "addEmployee";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@ModelAttribute(name = "employeeForm") Employee employee) {
        logger.info("Empleado -> " + employee);
        String username = createUsername(employee);
        encrypt(employee);
        employee.setUsername(username);
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/updateEmployee/{id}")
    public String showUpdate(@PathVariable(value = "id") String username,ModelMap map){
        Employee employee = employeeService.findEmployeeById(username);
        logger.info("Cliente a enviar -> " + employee);
        map.put("employee",employee);
        return "/updateEmployee";
    }

    @PostMapping("/updateEmployee")
    public String updateEmployee(@ModelAttribute(name = "employee") Employee employee){
        logger.info("Cliente a actualizar -> " + employee);
        if (!employee.getPassword().isEmpty()){
            encrypt(employee);
            logger.info("Diferente a null");
        }
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") String username){
        Employee employee = employeeService.findEmployeeById(username);
        logger.info("Cliente a eliminar -> " + employee);
        employeeService.deleteEmployee(employee);
        return "redirect:/employees";
    }

    private void encrypt(Employee employee) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode(employee.getPassword());
        employee.setPassword(password);
    }

    private String createUsername(Employee employee) {
        String name, lastName, phone;
        name = employee.getName();
        lastName = employee.getLastName();
        phone = employee.getPhone();
        String username;

        Random random = new Random();
        int n = random.nextInt(32, 127);
        char letter = (char) n;

        username = lastName.charAt(0) + name + lastName.charAt(lastName.length() - 1)
                + letter + "." + random.nextInt(1,99);
        return username.toLowerCase();
    }
}
