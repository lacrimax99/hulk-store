package com.hulk.store.application.exception;

public class ProductWithoutStockException extends StoreBusinessException {

    private static final String MESSAGE = "Product with id %s doesn't have enough stock";


    public ProductWithoutStockException(Integer idProduct) {
        super("Product", String.format(MESSAGE, idProduct));
    }
}
