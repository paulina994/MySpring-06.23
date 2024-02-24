package com.example.jsonex.service.impl;

import com.example.jsonex.model.dto.ProductNameAndPriceDto;
import com.example.jsonex.model.dto.ProductSeedDto;
import com.example.jsonex.model.entity.Product;
import com.example.jsonex.repository.ProductRepository;
import com.example.jsonex.service.CategoryService;
import com.example.jsonex.service.ProductService;
import com.example.jsonex.service.UserService;
import com.example.jsonex.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.jsonex.constants.GlobalConstants.RESOURCE_FILE_PATH;

@Service
public class ProductServiceImpl implements ProductService {
    private static final String PRODUCTS_FILE_NAME = "products.json";
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    private final UserService userService;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson, UserService userService, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.userService = userService;
        this.categoryService = categoryService;
    }


    @Override
    public void seedProducts() throws IOException {
        if (productRepository.count() > 0) {
            return;
        }

        if (productRepository.count() > 0) {
            return;
        }

        String fileContent = Files.readString(Path.of(RESOURCE_FILE_PATH + PRODUCTS_FILE_NAME));

        ProductSeedDto[] productSeedDtos = gson.fromJson(fileContent, ProductSeedDto[].class);

        Arrays.stream(productSeedDtos)
                .filter(validationUtil::isValid)
                .map(productSeedDto -> {
                    Product product = modelMapper.map(productSeedDto, Product.class);
                    product.setSeller(userService.findRandomUser());

                    if (product.getPrice().compareTo(BigDecimal.valueOf(500L)) > 0) {
                        product.setBuyer(userService.findRandomUser());
                    }

                    product.setCategories(categoryService.findRandomCategories());


                    return product;
                })
                .forEach(productRepository::save);

    }

    @Override
    public List<ProductNameAndPriceDto> findAllProductsInRangeOrderByPrice(BigDecimal lower, BigDecimal upper) {
        return productRepository
                .findAllByPriceBetweenAndBuyerIsNullOrderByPriceDesc(lower,upper)
                .stream()
                .map(product -> {
                    ProductNameAndPriceDto productNameAndPriceDto = modelMapper
                            .map(product,ProductNameAndPriceDto.class);

                    productNameAndPriceDto.setSeller(String.format("%s %s" ,
                            product.getSeller().getFirstName(),
                            product.getSeller().getLastName()));

                    return productNameAndPriceDto;
                })
                .collect(Collectors.toList());
    }
}
