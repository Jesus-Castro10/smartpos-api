package com.educastro.sales.model;

import jakarta.persistence.Column;
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
public class Employee {
    
    @Id
    private String username;
    private String password;
    private String name;
    
    @Column(name = "last_name")
    private String lastName;
    private String address;
    private String phone;
    private String post;

    public String getFullName(){
        return this.name + " " + this.lastName;
    }
}
