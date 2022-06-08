package com.hulk.store.application.service.product;

import com.hulk.store.application.exception.ProductNotExistException;
import com.hulk.store.application.rest.response.ProductResponse;
import com.hulk.store.domain.port.product.GetProductByIdServicePort;
import com.hulk.store.domain.port.product.ProductServiceImplPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetProductByIdService implements GetProductByIdServicePort {

    private ProductServiceImplPort productServiceImplPort;

    @Autowired
    public GetProductByIdService (ProductServiceImplPort productServiceImplPort){
        this.productServiceImplPort = productServiceImplPort;
    }

    public ResponseEntity<ProductResponse> executeService (Integer id) {

        try {
            var product = productServiceImplPort.getProductById(id);
            var productResponse = ProductResponse.fromModel(product);
            return ResponseEntity.status(HttpStatus.OK).body(productResponse);
        } catch (ProductNotExistException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
