package com.hulk.store.application.service.product;

import com.hulk.store.application.rest.response.ProductResponse;
import com.hulk.store.domain.port.product.GetProductServicePort;
import com.hulk.store.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetProductService implements GetProductServicePort {

    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<List<ProductResponse>> executeService () {
        var productsList = productRepository.findAll().stream()
                .map(ProductResponse::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productsList);
    }



}
