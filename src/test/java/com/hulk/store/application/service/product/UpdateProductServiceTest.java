package com.hulk.store.application.service.product;

import com.hulk.store.application.exception.ProductNotExistException;
import com.hulk.store.application.util.ProductTestUtil;
import com.hulk.store.application.util.UpdateProductRequestTestUtil;
import com.hulk.store.domain.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class UpdateProductServiceTest {

    @Mock
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private UpdateProductService addProductService;

    @BeforeEach
    public void setUp() {
        productService = mock(ProductService.class);
        productRepository = mock(ProductRepository.class);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void executeServiceOkTest() {
        var product = ProductTestUtil.getProduct();
        var productRequest = UpdateProductRequestTestUtil.getUpdateProductRequest();
        // WHEN
        Mockito.when(productService.getProductById(product.getId())).thenReturn(product);
        Mockito.when(productRepository.save(product)).thenReturn(product);

        var response = addProductService.executeService(product.getId(), productRequest);

        // THEN
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(HttpStatus.CREATED.value(), response.getStatusCodeValue()),
                () -> assertEquals(product.getId(), response.getBody().getId())
        );
    }

    @Test
    public void executeServiceFailureTest() {
        int productId = 100;
        var productRequest = UpdateProductRequestTestUtil.getUpdateProductRequest();
        // WHEN
        Mockito.when(productService.getProductById(productId)).thenThrow(new ProductNotExistException(productId));

        var response = addProductService.executeService(productId, productRequest);

        //THEN
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(String.format(ProductNotExistException.MESSAGE, productId), response.getBody());
    }

}