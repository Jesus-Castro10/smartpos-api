package com.educastro.sales.model.enums;

public enum ProductStatus {
    AVAILABLE("Disponible"),
    UNAVAILABLE("No disponible"),
    SOLDOUT("Agotado");

    private final String description;

    ProductStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
