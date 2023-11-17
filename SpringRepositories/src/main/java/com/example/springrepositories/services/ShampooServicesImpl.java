package com.example.springrepositories.services;

import com.example.springrepositories.entities.Shampoo;
import com.example.springrepositories.entities.Size;
import com.example.springrepositories.repositories.ShampooRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShampooServicesImpl implements ShampooService {

        private ShampooRepository shampooRepository;

    public ShampooServicesImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
        public List<Shampoo> findByBrand(String brand) {
            return shampooRepository.findByBrand(brand);
        }

        @Override
        public List<Shampoo> findByBrandAndSize(String brand, Size size) {
            return shampooRepository.findByBrandAndSize(brand, size);
        }

        @Override
        public List<Shampoo> findBySize(Size size) {
            return shampooRepository.findBySizeOrderById(size);
        }

        @Override
        public List<Shampoo> findBySizeOrLabelId(Size size, long labelId) {
            return shampooRepository.findBySizeOrLabelIdOrderByPrice(size, labelId);
        }

        @Override
        public List<Shampoo> findByPriceGreaterThan(BigDecimal price) {
            return shampooRepository.findByPriceGreaterThanOrderByPriceDesc(price);
        }

        @Override
        public int findCheaperThanCount(BigDecimal price) {
            return shampooRepository.countByPriceLessThan(price);
        }

        @Override
        public List<Shampoo> findAllWithIngredients(List<String> ingredientNames) {
            return shampooRepository.findByIngredientsNameIn(ingredientNames);
        }
    }