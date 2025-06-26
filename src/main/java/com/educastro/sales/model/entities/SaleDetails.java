package com.educastro.sales.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class SaleDetails {

    private Product product;
    private int amount;
    // private float subTotal;

    public SaleDetails(){}

    public SaleDetails(Product product) {
        this.product = product;
    }

    // public SaleDetails(Product product, int amount) {
    //     this.product = product;
    //     this.amount = amount;
    // }

    public float getSubTotal() {
        return product.getPrice() * amount;
    }
}
