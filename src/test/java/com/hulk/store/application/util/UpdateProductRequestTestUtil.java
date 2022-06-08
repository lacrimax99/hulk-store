package com.hulk.store.application.util;

import com.hulk.store.application.request.UpdateProductRequest;

import java.math.BigDecimal;

public class UpdateProductRequestTestUtil {

    public static UpdateProductRequest getUpdateProductRequest() {
        var updateProductRequest = new UpdateProductRequest();
        updateProductRequest.setName("Hulk");
        updateProductRequest.setPrice(BigDecimal.valueOf(100));
        return updateProductRequest;
    }
}
