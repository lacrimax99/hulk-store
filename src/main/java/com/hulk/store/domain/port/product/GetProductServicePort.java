package com.hulk.store.domain.port.product;

import org.springframework.http.ResponseEntity;

public interface GetProductServicePort<T> {

    ResponseEntity<T> executeService();

}
