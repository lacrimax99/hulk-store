package com.hulk.store.application.util;

import com.hulk.store.application.request.MovementRequest;
import com.hulk.store.domain.model.Movement;
import com.hulk.store.domain.model.MovementMethod;
import com.hulk.store.domain.model.Product;

import java.math.BigDecimal;

public class MovementTestUtil {

    public static Movement getMovementInvoice() {
        Movement movement = new Movement();
        movement.setProduct(new Product());
        movement.setAmount(10);
        movement.setValue(BigDecimal.valueOf(2500));
        movement.setMethod(MovementMethod.INVOICE);
        return movement;
    }

    public static Movement getMovementPurchase() {
        Movement movement = new Movement();
        movement.setProduct(new Product());
        movement.setAmount(10);
        movement.setValue(BigDecimal.valueOf(2500));
        movement.setMethod(MovementMethod.PURCHASE);
        return movement;
    }
}
