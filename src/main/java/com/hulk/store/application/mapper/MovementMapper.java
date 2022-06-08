package com.hulk.store.application.mapper;

import com.hulk.store.application.request.MovementRequest;
import com.hulk.store.domain.model.Movement;
import com.hulk.store.domain.model.MovementMethod;
import com.hulk.store.domain.model.Product;

import java.util.Date;

public class MovementMapper {

    public static Movement mapMovementToMovementKardexRequest(
            MovementRequest productRequest, Product product) {
        var movement = new Movement();
        movement.setProduct(product);
        movement.setAmount(productRequest.getAmount());
        movement.setValue(productRequest.getValue());
        movement.setDescription(productRequest.getDescription());
        movement.setCreatedAt(new Date());
        movement.setMethod(productRequest.getMethod().equals(MovementMethod.PURCHASE.toString()) ?
                MovementMethod.PURCHASE :
                MovementMethod.INVOICE);
        return movement;
    }
}
