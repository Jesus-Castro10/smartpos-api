package com.educastro.sales.service;

import com.educastro.sales.exception.ResourceNotFoundException;
import com.educastro.sales.model.dto.InvoiceDTO;
import com.educastro.sales.model.entities.Customer;
import com.educastro.sales.model.entities.Employee;
import com.educastro.sales.model.entities.Invoice;
import com.educastro.sales.repository.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class InvoiceService implements IInvoiceService{

    private final InvoiceRepository invoiceRepository;

    @Override
    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice save(InvoiceDTO invoiceDTO) {
        Invoice invoice = new Invoice();
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice update(InvoiceDTO invoiceDTO) {
        Invoice invoice = new Invoice();
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice findById(Integer id) {
        return invoiceRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void delete(Integer id) {
        Invoice invoice = findById(id);
        invoiceRepository.delete(invoice);
    }

    @Override
    public Invoice findByCustomer(Customer customer) {
        return invoiceRepository.findByCustomer(customer).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Invoice findByEmployee(Employee employee) {
        return invoiceRepository.findByEmployee(employee).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Invoice findByDate(Date date) {
        return invoiceRepository.findByDate(new Date()).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<Invoice> findAllByDate(Date date) {
        return invoiceRepository.findAllByDate(new Date());
    }
}
