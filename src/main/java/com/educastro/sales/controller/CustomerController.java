package com.educastro.sales.controller;

import com.educastro.sales.model.Customer;
import com.educastro.sales.model.dto.CustomerDTO;
import com.educastro.sales.service.CustomerService;
import com.educastro.sales.view.CustomerDataExcelExport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String showCustomer(ModelMap map) {
        List<Customer> customers = customerService.toListCustomer();
        map.put("customers", customers);
        return "customers";
    }

    @GetMapping("/add")
    public String showAddCustomer() {
        return "addCustomer";
    }

    @PostMapping("/add")
    public String addCustomer(@ModelAttribute("customerForm") CustomerDTO customer) {
        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/update/{id}")
    public String showUpdate(@PathVariable(value = "id") Integer idCustomer, ModelMap map) {
        System.out.println("Update customer");
        Customer customer = customerService.findCustomerById(idCustomer);
        map.put("customer", customer);
        return "updateCustomer";
    }

    @PostMapping("/update")
    public String updateCustomer(@ModelAttribute(name = "customer") Customer customer) {
        customerService.updateCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable(value = "id") Integer idCustomer) {
        customerService.deleteCustomer(idCustomer);
        return "redirect:/customers";
    }

    /***
     * Export data to excel file
     */
    @GetMapping("/customerExcelExport")
    public ModelAndView exportToExcel() {
        ModelAndView mav = new ModelAndView();
        mav.setView(new CustomerDataExcelExport());
        // read data from DB
        List<Customer> list = customerService.toListCustomer();
        // send to excelImpl class
        mav.addObject("list", list);
        return mav;
    }
}
