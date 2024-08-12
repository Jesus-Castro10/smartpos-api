package com.educastro.sales.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class NumericValidation implements ConstraintValidator<Numeric,Object> {

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (o == null) {
            return true;
        }
        if (o instanceof String) {
            try {
                Double.parseDouble((String) o);
                System.out.println("Try");
                return true;
            } catch (NumberFormatException e) {
                System.out.println("Catch");
                return false;
            }
        }
        return true;
    }
}
