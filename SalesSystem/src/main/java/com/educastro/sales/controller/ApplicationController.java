package com.educastro.sales.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ApplicationController {

    @GetMapping({"/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/products")
    public String books() {
        return "products";
    }

    @GetMapping("/sales")
    public String categories() {
        return "sales";
    }
}
