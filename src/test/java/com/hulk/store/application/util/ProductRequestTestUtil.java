package com.hulk.store.application.util;

import com.hulk.store.application.request.ProductRequest;

import java.math.BigDecimal;

public class ProductRequestTestUtil {

    public static ProductRequest getProductRequest() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setId(100);
        productRequest.setName("Hulk");
        productRequest.setPrice(BigDecimal.valueOf(100));
        return productRequest;
    }
}
