package org._07_automappingobjectsexercise.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.mapping.Constraint;

import java.util.Set;

public interface ValidatorService {
    <E>boolean isValid(E entity);
    <E> Set<ConstraintViolation<E>> validate(E entity);
}
