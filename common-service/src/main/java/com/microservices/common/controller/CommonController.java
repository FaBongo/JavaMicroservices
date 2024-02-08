package com.microservices.common.controller;

import com.microservices.common.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/product-details")
public class CommonController {

    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/{id}")
    public Product getProductDetails(@PathVariable("id") String id) {

        ResponseEntity<String> product = restTemplate.exchange("http://localhost:8081/api/product", HttpMethod.GET, null, String.class);
        ResponseEntity<String> category = restTemplate.exchange("http://localhost:8082/api/category", HttpMethod.GET, null, String.class);
        Product p = new Product();

        p.setName(product.getBody());
        p.setCategory(category.getBody());

        return p;
    }

}
