package com.educastro.sales.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String name;
    private String lastName;
    private String password;
    private boolean admin;
    private boolean cashier;
    private boolean customer;

    public UserDTO(String name, String lastName, String password) {
        this.name = name;
        this.lastName = lastName;
        this.password = password;
    }
}
