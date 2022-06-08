package com.hulk.store.application.rest.controller;

import com.hulk.store.application.request.ProductRequest;
import com.hulk.store.application.request.UpdateProductRequest;
import com.hulk.store.application.rest.response.AddProductResponse;
import com.hulk.store.application.rest.response.ProductResponse;
import com.hulk.store.application.service.product.UpdateProductService;
import com.hulk.store.domain.port.product.AddProductServicePort;
import com.hulk.store.domain.port.product.GetProductByIdServicePort;
import com.hulk.store.domain.port.product.GetProductServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    private GetProductServicePort getProductServicePort;

    private GetProductByIdServicePort getProductByIdServicePort;

    private AddProductServicePort addProductServicePort;

    private UpdateProductService updateProductService;

    @Autowired
    public ProductController(GetProductServicePort getProductServicePort,
                             AddProductServicePort addProductServicePort,
                             GetProductByIdServicePort getProductByIdServicePort,
                             UpdateProductService updateProductService) {
        this.getProductServicePort = getProductServicePort;
        this.addProductServicePort = addProductServicePort;
        this.getProductByIdServicePort = getProductByIdServicePort;
        this.updateProductService = updateProductService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll() {
        return getProductServicePort.executeService();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Integer productId) {
       return getProductByIdServicePort.executeService(productId);
    }

    @PostMapping
    public @ResponseBody ResponseEntity<AddProductResponse> save(@RequestBody @Valid ProductRequest requestBody) {
        return addProductServicePort.executeService(requestBody);
    }

    @PutMapping("/{productId}")
    public @ResponseBody ResponseEntity<ProductResponse> update(@PathVariable Integer productId,
                                                                @RequestBody @Valid UpdateProductRequest requestBody) {
        return updateProductService.executeService(productId, requestBody);
    }

}
