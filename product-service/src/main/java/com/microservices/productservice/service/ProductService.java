package com.microservices.productservice.service;

import com.microservices.productservice.dto.ProductCreate;
import com.microservices.productservice.dto.ProductList;
import com.microservices.productservice.model.Product;
import com.microservices.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    public void createProduct(ProductCreate productCreate){
        Product product = Product.builder()
                .name(productCreate.getName())
                .price(productCreate.getPrice())
                .description(productCreate.getDescription())
                .build();
        productRepository.save(product);
        log.info("Product {} is saved",product.getName());
    }

    public List<ProductList> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(this::mapToProductList).toList();
    }

    private ProductList mapToProductList(Product product) {
        return ProductList.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
    }
}
