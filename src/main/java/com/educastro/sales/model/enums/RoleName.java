package com.educastro.sales.model.enums;

public enum RoleName {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER"),
    ROLE_CASH("ROLE_CASH"),
    ROLE_CUSTOMER("ROLE_CUSTOMER");

    private final String name;

    RoleName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
