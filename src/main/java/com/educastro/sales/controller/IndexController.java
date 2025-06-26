package com.educastro.sales.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educastro.sales.service.EmailService;

/**
 *
 * @author Lenovo
 */
@RestController
@RequestMapping("/testing")
public class IndexController {
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @GetMapping
    public String init(){
        return passwordEncoder.encode("12345");
    }

    @GetMapping("/mail")
    public ResponseEntity<String> sendEmail() {
        emailService.sendEmail();
        return ResponseEntity.ok("Email sent successfully");
    }
}
