package com.hulk.store.application.exception;

public class ProductIdAlreadyExistsException extends StoreBusinessException {


    public static final String MESSAGE = "Product with id '%s' already exist";

    public ProductIdAlreadyExistsException(Integer id) {
        super("Product", String.format(MESSAGE, id));
    }
}
