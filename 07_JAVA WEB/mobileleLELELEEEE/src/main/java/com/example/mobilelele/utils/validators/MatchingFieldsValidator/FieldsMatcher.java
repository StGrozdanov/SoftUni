package com.example.mobilelele.utils.validators.MatchingFieldsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = MatchingFieldsValidator.class)
public @interface FieldsMatcher {
    String firstField();

    String secondField();

    String message() default "Passwords should match.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
