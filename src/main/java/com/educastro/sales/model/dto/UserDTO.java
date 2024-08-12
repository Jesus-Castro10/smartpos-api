package com.educastro.sales.model.dto;

import lombok.Data;

@Data
public class UserDTO {

    private String name;
    private String lastName;
    private String password;
    private boolean admin;
    private boolean cashier;
    private boolean customer;
}
