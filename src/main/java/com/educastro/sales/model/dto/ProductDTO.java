package com.educastro.sales.model.dto;

import com.educastro.sales.validation.NotNumeric;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

    @NotBlank
    @NotNumeric
    private String name;

    @NotNull
    @Min(1)
    private float price;

    @NotNull
    @Min(1)
    private int stock;

    private String description;

    private String image;

    @NotNull
    private int category;
}
