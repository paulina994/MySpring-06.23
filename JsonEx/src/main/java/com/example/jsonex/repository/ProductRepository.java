package com.example.jsonex.repository;

import com.example.jsonex.model.DTO.CategoriesByProductsSummaryDto;
import com.example.jsonex.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc(BigDecimal minPrice, BigDecimal maxPrice);

    @Query("SELECT new com.example.jsonex.model.DTO.CategoriesByProductsSummaryDto(" +
            "c.name, COUNT(p), AVG(p.price), SUM(p.price))" +
            "FROM Product p " +
            "JOIN p.categories c " +
            "GROUP BY c")
    List<CategoriesByProductsSummaryDto> getCategoryStatus();
}
