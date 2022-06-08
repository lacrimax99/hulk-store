package com.hulk.store.domain.port.product;

import com.hulk.store.application.exception.ProductIdAlreadyExistsException;
import com.hulk.store.application.exception.ProductNotExistException;
import com.hulk.store.domain.model.Product;

public interface ProductServiceImplPort {

    Product getProductById (Integer id) throws ProductNotExistException;

    void validateIfProductExists(Integer productId) throws ProductIdAlreadyExistsException;

    void validateIfProductNotExists(Integer productId) throws ProductIdAlreadyExistsException;
}
