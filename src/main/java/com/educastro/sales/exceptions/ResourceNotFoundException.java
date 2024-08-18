package com.educastro.sales.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("ResourceNotFoundException");
        System.out.println("Not found");
    }

    public ResourceNotFoundException(String message) {
        super(message);
        System.out.println("Not found");
    }
}
