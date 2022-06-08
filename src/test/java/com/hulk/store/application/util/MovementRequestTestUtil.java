package com.hulk.store.application.util;

import com.hulk.store.application.request.MovementRequest;
import com.hulk.store.application.request.ProductRequest;
import com.hulk.store.domain.model.MovementMethod;

import java.math.BigDecimal;

public class MovementRequestTestUtil {

    public static MovementRequest getMovementRequestInvoice() {
        MovementRequest movementRequest = new MovementRequest();
        movementRequest.setProductId(200);
        movementRequest.setAmount(10);
        movementRequest.setValue(BigDecimal.valueOf(2500));
        movementRequest.setMethod(MovementMethod.INVOICE.toString());
        return movementRequest;
    }

    public static MovementRequest getMovementRequestPurchase() {
        MovementRequest movementRequest = new MovementRequest();
        movementRequest.setProductId(100);
        movementRequest.setAmount(20);
        movementRequest.setValue(BigDecimal.valueOf(5000));
        movementRequest.setMethod(MovementMethod.PURCHASE.toString());
        return movementRequest;
    }


}
