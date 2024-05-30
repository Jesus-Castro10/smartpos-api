package com.educastro.sales.service;

import com.educastro.sales.model.Customer;
import com.educastro.sales.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Jesus Castro
 */
@Service
public class CustomerService implements ICustomerService{

    @Autowired
    private CustomerRepository customerRepository;
    
    @Override
    public List<Customer> toListCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findCustomerById(String idCustomer) {
        return customerRepository.findById(idCustomer).orElse(null);
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }
    
}
