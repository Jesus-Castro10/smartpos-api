package com.educastro.sales.controller;

import com.educastro.sales.model.Customer;
import com.educastro.sales.service.CustomerService;
import com.educastro.sales.view.CustomerDataExcelExport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public String showCustomer(ModelMap map){
        List<Customer> customers = customerService.toListCustomer();
        map.put("customers",customers);
        return "customers";
    }

    @GetMapping("/addCustomer")
    public String showAddCustomer(){
        return "addCustomer";
    }

    @PostMapping("/addCustomer")
    public String addCustomer(@ModelAttribute("customerForm")Customer customer){
        logger.info("Cliente a guardar -> " + customer);
        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/updateCustomer/{id}")
    public String showUpdate(@PathVariable(value = "id") String idCustomer,ModelMap map){
        Customer customer = customerService.findCustomerById(idCustomer);
        logger.info("Cliente a enviar update -> " + customer);
        map.put("customer",customer);
        return "updateCustomer";
    }

    @PostMapping("/updateCustomer")
    public String updateCustomer(@ModelAttribute(name = "customer") Customer customer){
        logger.info("Cliente a actualizar -> " + customer);
        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable(value = "id") String idCustomer){
        Customer customer = customerService.findCustomerById(idCustomer);
        logger.info("Cliente a eliminar -> " + customer);
        customerService.deleteCustomer(customer);
        return "redirect:/customers";
    }

    /***
     * Export data to excel file
     */
    @GetMapping("/customerExcelExport")
    public ModelAndView exportToExcel() {
        ModelAndView mav = new ModelAndView();
        mav.setView(new CustomerDataExcelExport());
        //read data from DB
        List<Customer> list= customerService.toListCustomer();
        //send to excelImpl class
        mav.addObject("list", list);
        return mav;
    }
}
