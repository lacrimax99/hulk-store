package com.hulk.store.application.exception;

public class ProductNotExistException extends StoreBusinessException {

    public static final String MESSAGE = "Product with id '%s' not exist";

    public ProductNotExistException(Integer id) {
        super("Product", String.format(MESSAGE, id));
    }
}
