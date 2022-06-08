package com.hulk.store.application.exception;

public class StoreBusinessException extends RuntimeException {

    private String code;

    public StoreBusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
