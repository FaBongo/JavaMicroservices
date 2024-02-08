package com.microservices.categoryservice.controller;

import com.microservices.categoryservice.dto.CategoryCreate;
import com.microservices.categoryservice.dto.CategoryList;
import com.microservices.categoryservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategory(@RequestBody CategoryCreate categoryCreate) {
        categoryService.createCategory(categoryCreate);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryList> getAllCategories() {
        return categoryService.getAllCategories();
    }



}
