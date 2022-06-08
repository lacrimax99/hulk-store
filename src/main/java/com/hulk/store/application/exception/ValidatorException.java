package com.hulk.store.application.exception;

public class ValidatorException extends StoreBusinessException {


    private static final String CODE = "Movement";

    public ValidatorException(String message) {
        super(CODE, message);
    }
}
