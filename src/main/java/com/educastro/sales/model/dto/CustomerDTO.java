package com.educastro.sales.model.dto;

import com.educastro.sales.model.entities.Customer;
import com.educastro.sales.validation.IsExist;
import com.educastro.sales.validation.NotNumeric;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerDTO {

    @Size(min = 3, max = 20)
    @NotBlank
    @NotNumeric
    private String name;

    @Size(min = 3, max = 20)
    @NotBlank
    @NotNumeric
    private String lastName;

    private String address;

    @Size(min = 7, max = 10)
    @NotBlank
    private String phone;

    @NotEmpty
    @Email
    private String email;

    @JsonProperty("user")
    private UserDTO userDTO;
}
