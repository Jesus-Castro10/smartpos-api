package com.educastro.sales.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Jesus Castro 
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    
    @Id
    private String idCustomer;
    private String name;
    private String lastName;
    private String address;
    private String phone;

    public Customer(String idCustomer, String name, String lastName) {
        this.idCustomer = idCustomer;
        this.name = name;
        this.lastName = lastName;
    }

    public String getFullName(){
        return this.name + " " + this.lastName;
    }
}
