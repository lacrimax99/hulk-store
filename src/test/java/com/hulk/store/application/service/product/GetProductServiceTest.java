package com.hulk.store.application.service.product;

import com.hulk.store.application.util.ProductTestUtil;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class GetProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private GetProductService getProductByIdService;

    @BeforeEach
    public void setUp() {
        productRepository = mock(ProductRepository.class);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getProductByIdServiceTest() {

        var product = ProductTestUtil.getProduct();
        Mockito.when(productRepository.findAll()).thenReturn(List.of(product));

        // WHEN
        var response = getProductByIdService.executeService();

        System.out.println(response);
        // THEN
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue()),
                () -> assertFalse(response.getBody().isEmpty()),
                () -> assertEquals(product.getId(), response.getBody().get(0).getId())
        );
    }

}