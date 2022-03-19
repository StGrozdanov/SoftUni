package com.example.football.util.validator;

import javax.validation.ConstraintViolation;
import java.util.Set;

public interface ValidationUtil {
    <T>Set<ConstraintViolation<T>> getViolations(T entity);

    <T> boolean isValid(T entity);
}
