package com.educastro.sales.service;

import com.educastro.sales.model.dto.InvoiceDTO;
import com.educastro.sales.model.entities.Customer;
import com.educastro.sales.model.entities.Employee;
import com.educastro.sales.model.entities.Invoice;

import java.util.Date;
import java.util.List;

public interface IInvoiceService {
    List<Invoice> findAll();
    Invoice save(InvoiceDTO invoice);
    Invoice update(InvoiceDTO invoice);
    Invoice findById(Integer id);
    void delete(Integer id);
    Invoice findByCustomer(Customer customer);
    Invoice findByEmployee(Employee employee);
    Invoice findByDate(Date date);
    List<Invoice> findAllByDate(Date date);
}
