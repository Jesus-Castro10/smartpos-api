package com.educastro.sales.service;

import com.educastro.sales.model.dto.CustomerDTO;
import com.educastro.sales.exception.ResourceNotFoundException;
import com.educastro.sales.model.entities.Customer;
import com.educastro.sales.model.entities.User;
import com.educastro.sales.model.enums.RoleName;
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
    private final ModelMapper mapper;
    private final UserService userService;
    private final EmailService emailService;
    
    @Override
    public List<Customer> toListCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findCustomerById(Integer idCustomer) {
        return customerRepository.findById(idCustomer).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Customer save(CustomerDTO customerDTO) {
        Customer customer = mapper.map(customerDTO, Customer.class);
        User user = userService.create(customerDTO.getName(), customerDTO.getLastname(), RoleName.ROLE_CUSTOMER.getName());
        customer.setUser(user);
        customer = customerRepository.save(customer);
        emailService.sendEmail(customer.getEmail(), "Welcome to Sales App", "Hello " + customer.getFullName() + ", welcome to Sales App!");
       return customer;
    }

    @Override
    public Customer updateCustomer(Integer idCustomer, CustomerDTO customerDTO) {
        Customer customer = findCustomerById(idCustomer);
        mapper.map(customerDTO,customer);
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
