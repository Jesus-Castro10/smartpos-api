package com.educastro.sales.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class IsNumericValidation implements ConstraintValidator<NotNumeric,String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !isNotNumeric(s);
    }

    public static boolean isNotNumeric(String value) {
        Pattern DIGIT_PATTERN = Pattern.compile("\\d");
        if (value == null || value.isEmpty()) {
            return false;
        }
        return DIGIT_PATTERN.matcher(value).find();
    }
}
