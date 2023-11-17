package com.example.springrepositories.services;

import com.example.springrepositories.entities.Shampoo;
import com.example.springrepositories.entities.Size;

import java.math.BigDecimal;
import java.util.List;

public interface ShampooService {

    List<Shampoo> findByBrand(String brand);

    List<Shampoo> findByBrandAndSize(String brand, Size size);

    List<Shampoo> findBySize(Size size);

    List<Shampoo> findBySizeOrLabelId(Size size, long labelId);

    List<Shampoo> findByPriceGreaterThan(BigDecimal price);

    int findCheaperThanCount(BigDecimal price);

    List<Shampoo> findAllWithIngredients(List<String> ingredientNames);
}