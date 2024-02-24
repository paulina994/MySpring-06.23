package com.example.jsonex.model.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Size;

public class CategorySeedDto {
    @Expose
    private String name;

    @Size(min = 3, max = 15)
    public String getName() {
        return name;
    }

    public CategorySeedDto() {
    }

    public void setName(String name) {
        this.name = name;

    }
}
