package com.educastro.sales.service;

import com.educastro.sales.model.dto.CustomerDTO;
import com.educastro.sales.exception.ResourceNotFoundException;
import com.educastro.sales.model.entities.Customer;
import com.educastro.sales.model.entities.User;
import com.educastro.sales.model.mapper.CustomerMapper;
import com.educastro.sales.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Jesus Castro
 */
@AllArgsConstructor
@Service
public class CustomerService implements ICustomerService{

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;
    private final UserService userService;
    
    @Override
    public List<Customer> toListCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findCustomerById(Integer idCustomer) {
        return customerRepository.findById(idCustomer).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Customer saveCustomer(CustomerDTO customerDTO) {
        Customer customer = mapper.map(customerDTO);
        User user = userService.create(customerDTO.getUserDTO());
        customer.setUser(user);
       return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Integer idCustomer, CustomerDTO customerDTO) {
        Customer customer = findCustomerById(idCustomer);
        mapper.map(customerDTO);
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Integer idCustomer) {
        Customer customer = findCustomerById(idCustomer);
        customerRepository.delete(customer);
    }

    @Override
    public Optional<Customer> findCustomerByUser(User user) {
        return customerRepository.findByUser(user);
    }

    @Override
    public boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

}
