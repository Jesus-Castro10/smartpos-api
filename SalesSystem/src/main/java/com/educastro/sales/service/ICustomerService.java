package com.educastro.sales.service;

import com.educastro.sales.model.Customer;

import java.util.List;

/**
 *
 * @author Jesus Castro
 */
public interface ICustomerService {
    public List<Customer> toListCustomer();
    public Customer findCustomerById(String idCustomer);
    public void saveCustomer(Customer customer);
    public void deleteCustomer(Customer customer);
}
