package com.example.mobilelele.utils.validators.MatchingFieldsValidator;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MatchingFieldsValidator implements ConstraintValidator<FieldsMatcher, Object> {
    private String firstField;
    private String secondField;
    private String message;

    @Override
    public void initialize(FieldsMatcher constraintAnnotation) {
        this.firstField = constraintAnnotation.firstField();
        this.secondField = constraintAnnotation.secondField();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);

        Object firstValue = wrapper.getPropertyValue(this.firstField);
        Object secondValue = wrapper.getPropertyValue(this.secondField);
        boolean isValid;

        if (firstValue == null) {
            isValid = secondValue == null;
        } else {
            isValid = firstValue.equals(secondValue);
        }

        if (!isValid) {
            context
                    .buildConstraintViolationWithTemplate(this.message)
                    .addPropertyNode(this.secondField)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }
        return isValid;
    }
}
