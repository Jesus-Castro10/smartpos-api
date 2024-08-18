package com.educastro.sales.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.educastro.sales.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(Exception.class)
    public ModelAndView exception(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("maintenance");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView resourceNotFoundException(ResourceNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("maintenance");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }
}
