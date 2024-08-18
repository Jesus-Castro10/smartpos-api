package com.educastro.sales.service;

import com.educastro.sales.model.Customer;
import com.educastro.sales.model.User;
import com.educastro.sales.model.dto.CustomerDTO;
import com.educastro.sales.model.dto.UserDTO;
import com.educastro.sales.repository.CustomerRepository;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Jesus Castro
 */
@AllArgsConstructor
@Service
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final UserService userService;
    private final ModelMapper mapper;

    @Override
    public List<Customer> toListCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findCustomerById(Integer idCustomer) {
        return customerRepository.findById(idCustomer).orElseThrow();
    }

    @Override
    public Customer saveCustomer(CustomerDTO customerDTO) {
        Customer customer = mapper.map(customerDTO, Customer.class);
        UserDTO userDTO = customerDTO.getUserDTO();
        userDTO.setCustomer(true);
        User user = userService.create(userDTO);
        customer.setUser(user);
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer customerDB = findCustomerById(customer.getIdCustomer());
        mapper.map(customer, customerDB);
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Integer idCustomer) {
        Customer customer = findCustomerById(idCustomer);
        customerRepository.delete(customer);
    }

}
