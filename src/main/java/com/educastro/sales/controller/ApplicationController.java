package com.educastro.sales.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ApplicationController {

    @GetMapping
    public String init() {
        return "index";
    }

    @GetMapping({ "/index" })
    public String index() {
        return "index";
    }

    @GetMapping("/maintenance")
    public String maintenance() {
        return "maintenance";
    }
}
