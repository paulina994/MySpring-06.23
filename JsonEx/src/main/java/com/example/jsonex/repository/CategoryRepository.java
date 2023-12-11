package com.example.jsonex.repository;

import com.example.jsonex.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends  JpaRepository<Category, Long> {
}
