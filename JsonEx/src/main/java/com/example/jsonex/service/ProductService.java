package com.example.jsonex.service;


import com.example.jsonex.model.DTO.CategoriesByProductsSummaryDto;
import com.example.jsonex.model.DTO.ProductNamePriceAndSellerDto;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void seedProducts() throws IOException;

    List<ProductNamePriceAndSellerDto> findAllProductsInRangeOrderByPrice(BigDecimal minPrice, BigDecimal maxPrice);

    List<CategoriesByProductsSummaryDto> getCategoryStatistics();
}