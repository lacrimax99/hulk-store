package com.hulk.store.application.mapper;

import com.hulk.store.application.request.ProductRequest;
import com.hulk.store.application.request.UpdateProductRequest;
import com.hulk.store.domain.model.Product;

public class ProductMapper {

    public static Product mapProductToProductRequest(ProductRequest productRequest) {
        Product product = new Product();
        product.setId(productRequest.getId());
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        return product;
    }

    public static void mapProductToProductRequest(Product product, UpdateProductRequest productRequest) {
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
    }

}
