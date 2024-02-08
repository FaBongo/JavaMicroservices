package com.microservices.productservice.dto;

import jakarta.persistence.Entity;
import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductCreate {

    private String name;
    private double price;
    private String description;

}
