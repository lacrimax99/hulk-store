package com.hulk.store.application.service.product;

import com.hulk.store.application.exception.ProductIdAlreadyExistsException;
import com.hulk.store.application.mapper.ProductMapper;
import com.hulk.store.application.request.ProductRequest;
import com.hulk.store.application.rest.response.AddProductResponse;
import com.hulk.store.domain.port.product.AddProductServicePort;
import com.hulk.store.domain.port.product.ProductServiceImplPort;
import com.hulk.store.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddProductService implements AddProductServicePort {

    private ProductRepository productRepository;

    private ProductServiceImplPort productServiceImplPort;

    @Autowired
    public AddProductService(ProductRepository productRepository,
                             ProductServiceImplPort productServiceImplPort) {
        this.productRepository =productRepository;
        this.productServiceImplPort = productServiceImplPort;
    }

    public ResponseEntity<AddProductResponse> executeService(ProductRequest productRequest) {
        try {
            var productId = productRequest.getId();
            productServiceImplPort.validateIfProductExists(productId);
            productRepository.save(ProductMapper.mapProductToProductRequest(productRequest));
            return ResponseEntity.status(HttpStatus.OK).body(buildResponse(productId));
        } catch (ProductIdAlreadyExistsException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private AddProductResponse buildResponse(Integer productId) {
        return AddProductResponse.builder().id(productId).build();
    }
}
