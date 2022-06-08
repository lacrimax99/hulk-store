package com.hulk.store.domain.port.product;

import com.hulk.store.application.request.ProductRequest;
import org.springframework.http.ResponseEntity;

public interface AddProductServicePort<T> {

    ResponseEntity<T> executeService(ProductRequest oDTO);
}
