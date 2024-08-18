package com.educastro.sales.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author Jesus Castro
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sale")
    private Integer idSale;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private Date date;
    
    private LocalTime hour;

    @Column(columnDefinition = "TEXT")
    private String products;

    private double total;

    public Sale(Customer customer, Employee employee, Date date, LocalTime hour, String products,double total) {
        this.customer = customer;
        this.employee = employee;
        this.date = date;
        this.hour = hour;
        this.products = products;
        this.total = total;
    }
}
