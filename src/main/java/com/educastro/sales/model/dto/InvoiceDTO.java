package com.educastro.sales.model.dto;

import com.educastro.sales.model.entities.Customer;
import com.educastro.sales.model.entities.Employee;
import com.educastro.sales.model.entities.SaleDetails;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class InvoiceDTO {

    private Employee employee;

    private Customer customer;

    private double total;

    private Date date;

    private byte[] report;

    @JsonProperty("products")
    private List<SaleDetails> saleDetails;
}
