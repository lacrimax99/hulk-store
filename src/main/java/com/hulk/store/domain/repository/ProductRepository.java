package com.hulk.store.domain.repository;

import com.hulk.store.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
