package com.hulk.store.application.util;

import com.hulk.store.domain.model.Product;

import java.math.BigDecimal;

public class ProductTestUtil {

    public static Product getProduct() {
        return Product.builder()
                .id(100)
                .name("Hulk")
                .price(BigDecimal.valueOf(15000))
                .build();

    }

}
