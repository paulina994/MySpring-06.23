package com.example.xmlex.services;

import com.example.xmlex.modules.dtos.ProductSeedDto;
import com.example.xmlex.modules.dtos.ProductViewRootDto;

import java.util.List;

public interface ProductService {
    long getCount();

    void seedProducts(List<ProductSeedDto> products);

    ProductViewRootDto findProductsInRangeWithoutBuyer();
}
