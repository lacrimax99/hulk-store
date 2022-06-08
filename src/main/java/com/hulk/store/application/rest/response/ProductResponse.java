package com.hulk.store.application.rest.response;

import com.hulk.store.domain.model.Product;

import java.math.BigDecimal;

public class ProductResponse {

    private Integer id;

    private String name;

    private BigDecimal price;

    public ProductResponse(Integer id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public static ProductResponse fromModel(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getPrice());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
