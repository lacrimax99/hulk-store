package com.hulk.store;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ComponentScan(basePackages = { "com.hulk.store",  "com.hulk.store.domain.repository" })
public class HulkStoreApplicationTests {

	@Autowired
	private HulkStoreApplication application;

	@Test
	void contextLoads() {
	}

}
