package com.educastro.sales.controller;

import com.educastro.sales.model.*;
import com.educastro.sales.service.CustomerService;
import com.educastro.sales.service.EmployeeService;
import com.educastro.sales.service.ProductService;
import com.educastro.sales.service.SaleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SaleService saleService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Sale> getSales() {
        return saleService.toListSale();
    }

    @GetMapping("/{id}")
    public Sale getSale(@PathVariable(value = "id") Integer idSale) {
        return saleService.findSaleById(idSale);
    }

    @PostMapping
    public Sale saveSale(@RequestBody List<SaleDetails> saleDetails,@PathVariable String idCustomer,@AuthenticationPrincipal User user) {
        Employee employee = employeeService.findEmployeeById(user.getUsername());
        Customer customer = customerService.findCustomerById(idCustomer);
        return saleService.saveSale(employee,customer,saleDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteSale(@PathVariable(value = "id") Integer idSale) {
        var sale = saleService.findSaleById(idSale);
        saleService.deleteSale(sale);
    }

//    @GetMapping("/printSale")
//    public String printSale() {
//        var sale = saleService.findSaleById(1);
//        recoveryProducts(sale);
//        return "redirect:/salesHistory";
//    }

//    private List<SaleDetails> recoveryProducts(Sale sale) {
//        JSONArray products = new JSONArray(sale.getProducts());
//        List<SaleDetails> salesDetails = new ArrayList<>();
//        for (int i = 0; i < products.length(); i++) {
//            JSONObject product = products.getJSONObject(i);
//            var name = product.getString("name");
//            var p = productService.findProductByName(name);
//            var amount = product.getInt("amount");
//            var saleDetails = new SaleDetails(p, amount);
//            salesDetails.add(saleDetails);
//        }
//        for (SaleDetails saleDetails1 : salesDetails) {
//            logger.info(saleDetails1.toString());
//        }
//        logger.info("product json => " + products);
//        return salesDetails;
//    }
}
