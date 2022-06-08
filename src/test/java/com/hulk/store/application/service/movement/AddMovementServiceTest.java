package com.hulk.store.application.service.movement;

import com.hulk.store.application.service.product.ProductService;
import com.hulk.store.application.util.MovementRequestTestUtil;
import com.hulk.store.application.util.MovementTestUtil;
import com.hulk.store.domain.model.Movement;
import com.hulk.store.domain.repository.MovementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AddMovementServiceTest {

    @Mock
    private MovementRepository movementRepository;

    @Mock
    private ProductService productService;

    @InjectMocks
    private AddMovementService addMovementService;

    @BeforeEach
    public void setUp() {
        movementRepository = mock(MovementRepository.class);
        productService = mock(ProductService.class);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void executeServiceAmountFailureTest() {
        var movementRequestInvoice = MovementRequestTestUtil.getMovementRequestInvoice();
        movementRequestInvoice.setAmount(0);

        // WHEN
        var response = addMovementService.executeService(movementRequestInvoice);

        //THEN
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("amount cannot be zero or negative", response.getBody());
    }

    @Test
    void executeServiceValueFailureTest() {
        var movementRequestInvoice = MovementRequestTestUtil.getMovementRequestInvoice();
        movementRequestInvoice.setValue(BigDecimal.ZERO);

        // WHEN
        var response = addMovementService.executeService(movementRequestInvoice);

        //THEN
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("value cannot be zero or negative", response.getBody());
    }

    @Test
    void executeServiceNotAmountStockTest() {
        var movementRequestInvoice = MovementRequestTestUtil.getMovementRequestInvoice();
        var movementList = getMovementList();
        var productId = movementRequestInvoice.getProductId();
        // WHEN
        when(movementRepository.findAllMovementsByProductId(productId)).thenReturn(movementList);
        var response = addMovementService.executeService(movementRequestInvoice);

        //THEN
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(String.format("Product with id %s doesn't have enough stock", productId), response.getBody());
    }

    private List<Movement> getMovementList() {
        return List.of(MovementTestUtil.getMovementPurchase(),
                MovementTestUtil.getMovementInvoice());
    }


}