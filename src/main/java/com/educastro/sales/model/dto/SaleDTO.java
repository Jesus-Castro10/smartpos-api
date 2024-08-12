package com.educastro.sales.model.dto;

import com.educastro.sales.model.entities.Customer;
import com.educastro.sales.model.entities.Employee;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleDTO {

    @NotNull
    private Employee employee;

    @NotNull
    @DecimalMin("1")
    private double total;

    @NotNull
    @JsonProperty("invoice")
    private InvoiceDTO invoiceDTO;

}
