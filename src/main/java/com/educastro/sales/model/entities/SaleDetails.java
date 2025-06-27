package com.educastro.sales.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Entity
@Table(name = "sale_details")
public class SaleDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private int amount;
    private float subTotal;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    public SaleDetails(Product product) {
        this.product = product;
    }

    public SaleDetails(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public float getSubTotal() {
        return product.getPrice() * amount;
    }
}
