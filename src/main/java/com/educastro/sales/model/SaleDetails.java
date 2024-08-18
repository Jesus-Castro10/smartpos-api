package com.educastro.sales.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class SaleDetails {

    private Product product;
    private int amount;
    private float subTotal;

    public SaleDetails(){
        product = new Product("xxxx",0);
        amount = 0;
        subTotal = 0;
    }

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
