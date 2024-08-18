package com.educastro.sales.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    @Column(name = "idCustomer")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCustomer;

    private String name;

    @Column(name = "last_name")
    private String lastName;

    private String address;

    private String phone;

    private String email;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id_user")
    private User user;

    public Customer(Integer idCustomer, String name, String lastName) {
        this.idCustomer = idCustomer;
        this.name = name;
        this.lastName = lastName;
    }

    public String getFullName(){
        return this.name + " " + this.lastName;
    }
}
