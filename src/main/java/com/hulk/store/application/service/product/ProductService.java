package com.hulk.store.application.service.product;

import com.hulk.store.application.exception.ProductIdAlreadyExistsException;
import com.hulk.store.application.exception.ProductNotExistException;
import com.hulk.store.domain.model.Product;
import com.hulk.store.domain.port.product.ProductServiceImplPort;
import com.hulk.store.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements ProductServiceImplPort {

    @Autowired
    private ProductRepository productRepository;

    public Product getProductById (Integer ProductId) throws ProductNotExistException {
        Optional<Product> product = productRepository.findById(ProductId);
        if (!product.isPresent()) {
            throw new ProductNotExistException(ProductId);
        }
        return product.get();
    }

    public void validateIfProductExists(Integer productId) throws ProductIdAlreadyExistsException {

        Optional<Product> optional = productRepository.findById(productId);
        if(optional.isPresent()) {
            throw new ProductIdAlreadyExistsException(productId);
        }
    }

    public void validateIfProductNotExists(Integer productId) throws ProductIdAlreadyExistsException {

        Optional<Product> optional = productRepository.findById(productId);
        if(!optional.isPresent()) {
            throw new ProductNotExistException(productId);
        }
    }


}
