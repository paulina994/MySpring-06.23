package com.example.springrepositories.services;

import com.example.springrepositories.entities.Ingredient;

import java.util.List;

public interface IngredientsService {
    List<Ingredient> findByName(String name);

    List<Ingredient> findByNameWithin(List<String> names);

    void increasePrice();

    void deleteByName(String name);
}
