package com.hulk.store.application;

import com.hulk.store.application.exception.ValidatorException;

import java.math.BigDecimal;

public class ValidatorUtil {

    public static void notZeroOrNegative(Integer value, String fieldName) {

        notNull(value, fieldName);

        if(value.intValue() < 1) {
            throw new ValidatorException(String.format("%s cannot be zero or negative", fieldName));
        }

    }

    public static void notZeroOrNegative(BigDecimal value, String fieldName) {

        notNull(value, fieldName);

        if(value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidatorException(String.format("%s cannot be zero or negative", fieldName));
        }

    }

    public static void notNull(Object object, String fieldName) {
        if(object == null) {
            throw new ValidatorException(String.format("'%s cannot be null'", fieldName));
        }
    }
}
