package com.hulk.store.application.service.product;

import com.hulk.store.application.exception.ProductNotExistException;
import com.hulk.store.application.util.ProductTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GetProductByIdServiceTest {

    @Mock
    private ProductService productService;

    private GetProductByIdService getProductByIdService;

    @BeforeEach
    public void setUp() {
        getProductByIdService = new GetProductByIdService(productService);
    }

    @Test
    public void getProductByIdServiceTest() {

        var product = ProductTestUtil.getProduct();
        Mockito.when(productService.getProductById(100)).thenReturn(product);

        // WHEN
        var response = getProductByIdService.executeService(100);

        // THEN
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue()),
                () -> assertEquals(product.getId(), response.getBody().getId())
        );
    }

    @Test
    public void getProductByIdServiceFailureTest() {
        int productId = 100;
        Mockito.when(productService.getProductById(productId)).thenThrow(new ProductNotExistException(productId));

        // WHEN
        var response = getProductByIdService.executeService(productId);

        //THEN
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(String.format(ProductNotExistException.MESSAGE, productId), response.getBody());
    }


}