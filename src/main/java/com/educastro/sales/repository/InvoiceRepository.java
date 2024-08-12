package com.educastro.sales.repository;

import com.educastro.sales.model.entities.Customer;
import com.educastro.sales.model.entities.Employee;
import com.educastro.sales.model.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice,Integer> {

    Optional<Invoice> findByCustomer(Customer customer);
    Optional<Invoice> findByEmployee(Employee employee);
    Optional<Invoice> findByDate(Date date);
    List<Invoice> findAllByDate(Date date);
}
