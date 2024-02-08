package com.microservices.categoryservice.service;

import com.microservices.categoryservice.dto.CategoryCreate;
import com.microservices.categoryservice.dto.CategoryList;
import com.microservices.categoryservice.model.Category;
import com.microservices.categoryservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;
    public void createCategory(CategoryCreate categoryCreate){
        Category category = Category.builder()
                .name(categoryCreate.getName())
                .description(categoryCreate.getDescription())
                .parent_id(categoryCreate.getParent_id())
                .build();
        categoryRepository.save(category);
        log.info("Category {} is saved",category.getName());
    }

    public List<CategoryList> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream().map(this::mapToCategoryList).toList();
    }

    private CategoryList mapToCategoryList(Category category) {
        return CategoryList.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .parent_id(category.getParent_id())
                .build();
    }
}
