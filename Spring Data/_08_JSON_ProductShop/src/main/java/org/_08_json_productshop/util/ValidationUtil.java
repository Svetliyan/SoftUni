package org._08_json_productshop.util;

import jakarta.validation.ConstraintViolation;
import org.aspectj.apache.bcel.classfile.Constant;

import java.util.Set;

public interface ValidationUtil {
    <E> boolean isValid(E entity);
    <E>Set<ConstraintViolation<E>> getViolations(E entity);
}
