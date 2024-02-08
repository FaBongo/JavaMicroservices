package com.microservices.productservice.controller;

import com.microservices.productservice.dto.ProductCreate;
import com.microservices.productservice.dto.ProductList;
import com.microservices.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductCreate productCreate) {
        productService.createProduct(productCreate);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductList> getAllProducts()  {
        return productService.getAllProducts();
    }



}
