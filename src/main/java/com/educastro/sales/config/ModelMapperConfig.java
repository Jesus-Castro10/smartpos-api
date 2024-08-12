package com.educastro.sales.config;

import com.educastro.sales.model.mapper.CustomerMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    CustomerMapper customerMapper(){
        return new CustomerMapper();
    }
}
