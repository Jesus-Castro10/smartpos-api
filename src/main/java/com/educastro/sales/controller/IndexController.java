package com.educastro.sales.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Lenovo
 */
@Controller
public class IndexController {
    
    @GetMapping("/")
    public String init(){
        return "index";
    }

}
