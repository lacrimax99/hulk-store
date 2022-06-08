package com.hulk.store.application.service.product;

import com.hulk.store.application.exception.ProductNotExistException;
import com.hulk.store.application.mapper.ProductMapper;
import com.hulk.store.application.request.UpdateProductRequest;
import com.hulk.store.application.rest.response.ProductResponse;
import com.hulk.store.domain.port.product.ProductServiceImplPort;
import com.hulk.store.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductService {

    private ProductRepository productRepository;

    private ProductServiceImplPort productServiceImplPort;

    @Autowired
    public UpdateProductService(ProductRepository productRepository,
                                ProductServiceImplPort productServiceImplPort) {
        this.productRepository = productRepository;
        this.productServiceImplPort = productServiceImplPort;
    }

    public ResponseEntity<ProductResponse> executeService (int productId, UpdateProductRequest productRequest) {
        try {
            var product = productServiceImplPort.getProductById(productId);
            ProductMapper.mapProductToProductRequest(product, productRequest);
            productRepository.save(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(ProductResponse.fromModel(product));
        } catch (ProductNotExistException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
