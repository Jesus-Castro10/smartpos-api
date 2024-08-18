package com.educastro.sales.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.educastro.sales.model.Product;
import com.educastro.sales.service.ProductService;

@Controller
public class SaleController {

    @Autowired
    private ProductService productService;
    // @Autowired
    // private CustomerService customerService;
    //
    // @Autowired
    // private SaleService saleService;
    //
    // @Autowired
    // private EmployeeService employeeService;

    @GetMapping("/sales")
    public String getSales(ModelMap map) {
        List<Product> products = productService.findAll();
        map.put("products", products);
        return "sales";
    }

    // @GetMapping("/{id}")
    // public Sale getSale(@PathVariable(value = "id") Integer idSale) {
    // return saleService.findSaleById(idSale);
    // }
    //
    // @PostMapping
    // public Sale saveSale(@RequestBody List<SaleDetails> saleDetails,@PathVariable
    // String idCustomer,@AuthenticationPrincipal User user) {
    // Employee employee = employeeService.findEmployeeById(user.getUsername());
    // Customer customer = customerService.findCustomerById(idCustomer);
    // return saleService.saveSale(employee,customer,saleDetails);
    // }
    //
    // @DeleteMapping("/{id}")
    // public void deleteSale(@PathVariable(value = "id") Integer idSale) {
    // var sale = saleService.findSaleById(idSale);
    // saleService.deleteSale(sale);
    // }

    // @GetMapping("/printSale")
    // public String printSale() {
    // var sale = saleService.findSaleById(1);
    // recoveryProducts(sale);
    // return "redirect:/salesHistory";
    // }

    // private List<SaleDetails> recoveryProducts(Sale sale) {
    // JSONArray products = new JSONArray(sale.getProducts());
    // List<SaleDetails> salesDetails = new ArrayList<>();
    // for (int i = 0; i < products.length(); i++) {
    // JSONObject product = products.getJSONObject(i);
    // var name = product.getString("name");
    // var p = productService.findProductByName(name);
    // var amount = product.getInt("amount");
    // var saleDetails = new SaleDetails(p, amount);
    // salesDetails.add(saleDetails);
    // }
    // for (SaleDetails saleDetails1 : salesDetails) {
    // logger.info(saleDetails1.toString());
    // }
    // logger.info("product json => " + products);
    // return salesDetails;
    // }
}
