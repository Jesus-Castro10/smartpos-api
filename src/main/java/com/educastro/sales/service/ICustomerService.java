package com.educastro.sales.service;

import com.educastro.sales.model.Customer;
import com.educastro.sales.model.dto.CustomerDTO;

import java.util.List;

/**
 *
 * @author Jesus Castro
 */
public interface ICustomerService {
    public List<Customer> toListCustomer();

    public Customer findCustomerById(Integer idCustomer);

    public Customer saveCustomer(CustomerDTO customer);

    public Customer updateCustomer(Customer customer);

    public void deleteCustomer(Integer idCustomer);
}
