package com.microservices.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.productservice.dto.ProductCreate;
import com.microservices.productservice.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

	@Container
	static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:14.5");

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private ProductRepository productRepository;
	/*
	static {
		postgreSQLContainer.start();
	}*/

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.datasource.url",postgreSQLContainer::getJdbcUrl);
	}


	@Test
	void shouldCreateProduct() throws Exception {
		ProductCreate productCreate = getProductCreate();
		String productCreateString = objectMapper.writeValueAsString(productCreate);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
						.contentType(MediaType.APPLICATION_JSON)
						.content(productCreateString))
						.andExpect(status().isCreated());
		Assertions.assertEquals(1, productRepository.findAll().size());
	}

	private ProductCreate getProductCreate() {
		return ProductCreate.builder()
				.name("Lait")
				.price(1200)
				.description("Lait de vache")
				.build();
	}


}
