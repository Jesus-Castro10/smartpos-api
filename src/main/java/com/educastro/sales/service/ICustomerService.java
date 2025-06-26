package com.educastro.sales.service;

import com.educastro.sales.model.dto.CustomerDTO;
import com.educastro.sales.model.entities.Customer;
import com.educastro.sales.model.entities.User;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Jesus Castro
 */
public interface ICustomerService {
    public List<Customer> toListCustomer();
    public Customer findCustomerById(Integer idCustomer);
    public Customer save(CustomerDTO customer);
    public Customer updateCustomer(Integer idCustomer,CustomerDTO customerDTO);
    public void deleteCustomer(Integer idCustomer);
    Optional<Customer> findCustomerByUser(User user);
    public boolean existsByEmail(String email);
}
