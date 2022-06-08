package com.hulk.store.domain.port.product;

import org.springframework.http.ResponseEntity;

public interface GetProductByIdServicePort<T> {

    ResponseEntity<T> executeService(Integer productId);

}
