package com.educastro.sales.validation;

import com.educastro.sales.model.entities.Customer;
import com.educastro.sales.model.entities.Product;
import com.educastro.sales.service.CategoryService;
import com.educastro.sales.service.CustomerService;
import com.educastro.sales.service.ProductService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IsExistsValidation implements ConstraintValidator<IsExist,String> {

    @Autowired
    private final CategoryService categoryService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    private Class<?> type;

    IsExistsValidation(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void initialize(IsExist constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.type = constraintAnnotation.type();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return isExistDB(s);
    }

    private boolean isExistDB(String value){
        if (type.equals(Customer.class)){
            return !customerService.existsByEmail(value);
        }
        if (type.equals(Product.class)){
            return !productService.existsByName(value);
        }
        if (type.equals(Category.class)){
            return !categoryService.existsByName(value);
        }
        return false;
    }
}
