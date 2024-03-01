package com.example.xmlex.services;

import com.example.xmlex.modules.dtos.CategorySeedDto;
import com.example.xmlex.modules.entities.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    void seedCategories(List<CategorySeedDto> categories);

    long getEntityCount();

    Set<Category> getRandomCategories();
}
