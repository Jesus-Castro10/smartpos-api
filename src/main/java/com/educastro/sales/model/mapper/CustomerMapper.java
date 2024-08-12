package com.educastro.sales.model.mapper;

import com.educastro.sales.model.dto.CustomerDTO;
import com.educastro.sales.model.entities.Customer;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerMapper {

    private ModelMapper modelMapper;
    public CustomerMapper() {
        modelMapper = new ModelMapper();
    }

//    public Customer map(CustomerDTO customerDTO){
//        TypeMap<Customer, CustomerDTO> propertyMapper = this.createTypeMap(Customer.class, CustomerDTO.class);
//        propertyMapper.addMappings(mapper -> mapper.skip(CustomerDTO::setUserDTO));
//        return this.map(CustomerDTO.class, Customer.class);
//    }

//    public Customer map(CustomerDTO customerDTO){
//        modelMapper.addMappings(new PropertyMap<CustomerDTO, Customer>() {
//            @Override
//            protected void configure() {
//                skip(destination.getUser());
//            }
//        });
//        System.out.println("customerDTO from mapper class = " + customerDTO);
//        System.out.println("customer from model.map -> " + modelMapper.map(CustomerDTO.class, Customer.class));
//        return modelMapper.map(CustomerDTO.class, Customer.class);
//    }

    public Customer map(CustomerDTO customerDTO){
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customerDTO.getPhone());
        customer.setAddress(customerDTO.getAddress());
        return customer;
    }

    public CustomerDTO mapToDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName(customer.getName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhone(customer.getPhone());
        customerDTO.setAddress(customer.getAddress());
        return customerDTO;
    }
}
