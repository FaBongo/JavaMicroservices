package com.microservices.categoryservice.repository;

import com.microservices.categoryservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
