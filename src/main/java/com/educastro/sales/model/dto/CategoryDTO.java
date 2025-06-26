package com.educastro.sales.model.dto;


import com.educastro.sales.model.entities.Category;
import com.educastro.sales.validation.IsExist;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {

    @NotBlank
    @IsExist(type = Category.class, message = "Category already exists")
    private String name;

    private String description;
}
