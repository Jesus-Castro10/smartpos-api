package com.educastro.sales.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {

    private String idCard;

    @NotBlank
    private String firstname;

    @NotBlank
    private String secondname;

    @NotBlank
    private String firstLastname;

    @NotBlank
    private String secondLastname;

    @NotBlank
    private String address;

    @Size(min = 7, max = 10)
    @NotBlank
    private String phone;

    @NotEmpty
    @Email
    private String email;

    @NotBlank
    private String post;

    @JsonIgnore
    private UserDTO userDTO;
}
