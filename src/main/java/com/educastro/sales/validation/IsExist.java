package com.educastro.sales.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.apache.commons.codec.language.bm.Lang;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IsExistsValidation.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IsExist {
    String message() default "yes exist in db";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?> type() default String.class;
}
