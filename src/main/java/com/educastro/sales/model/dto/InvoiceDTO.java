package com.educastro.sales.model.dto;

import com.educastro.sales.model.Customer;
import com.educastro.sales.model.Employee;
import com.educastro.sales.model.SaleDetails;
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
