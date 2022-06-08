package com.hulk.store;

import com.hulk.store.domain.model.Product;
import com.hulk.store.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class HulkStoreApplication implements ApplicationRunner {

	//@Autowired
	private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(HulkStoreApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
	/*	Product product = new Product();
		product.setId(100);
		product.setName("Hulk");
		product.setPrice(BigDecimal.valueOf(100));
		productRepository.save(product);*/
	}
}
