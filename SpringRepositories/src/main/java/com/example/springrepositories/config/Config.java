package com.example.springrepositories.config;

import com.example.springrepositories.repositories.IngredientsRepository;
import com.example.springrepositories.services.IngredientsService;
import com.example.springrepositories.services.IngredientsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class Config {

    private Environment environment;

    public Config(Environment environment) {
        this.environment = environment;

        int property = this.environment.getProperty("bg.softuni.max-shampoos", int.class);

        System.out.println(property);
    }


    @Bean
    public IngredientsService createIngredientsService(IngredientsRepository ingredientRepo) {
        return new IngredientsServiceImpl(ingredientRepo);
    }
}