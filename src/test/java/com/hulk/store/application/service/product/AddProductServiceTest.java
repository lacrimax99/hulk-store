package com.hulk.store.application.service.product;

import com.hulk.store.application.exception.ProductIdAlreadyExistsException;
import com.hulk.store.application.util.ProductRequestTestUtil;
import com.hulk.store.application.util.ProductTestUtil;
import com.hulk.store.domain.port.product.ProductServiceImplPort;
import com.hulk.store.domain.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

@ExtendWith(SpringExtension.class)
class AddProductServiceTest {

    @Mock
    private ProductServiceImplPort productService;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private AddProductService addProductService;

    @BeforeEach
    public void setUp() {
        productService = mock(ProductServiceImplPort.class);
        productRepository = mock(ProductRepository.class);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addProductServiceTest() {
        var product = ProductTestUtil.getProduct();
        var productRequest = ProductRequestTestUtil.getProductRequest();
        // WHEN
        Mockito.when(productRepository.findById(1)).thenReturn(Optional.of(product));

        var response = addProductService.executeService(productRequest);

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
        doThrow(new ProductIdAlreadyExistsException(productId)).when(productService).validateIfProductExists(anyInt());
        var productRequest = ProductRequestTestUtil.getProductRequest();

        // WHEN
        var response = addProductService.executeService(productRequest);

        //THEN
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(String.format(ProductIdAlreadyExistsException.MESSAGE, productId), response.getBody());
    }

}