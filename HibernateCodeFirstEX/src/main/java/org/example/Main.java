package org.example;


import entity.Product;
import entity.Sale;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDateTime;


public class Main {
    public static void main(String[] args) {
        EntityManager entityManager = Persistence
                .createEntityManagerFactory("test")
                .createEntityManager();

        entityManager.getTransaction().begin();

//        Sale sale = new Sale();
        //      sale.setDateTime(LocalDateTime.now());

        //      Product product = new Product();
        //    product.setName("testProduct");
        //  product.setPrice(BigDecimal.TEN);
        //product.setQuantity(5);
        //product.getSales().add(sale);
        //sale.setProduct(product);

//        entityManager.persist(product);

        entityManager.getTransaction().commit();

    }
}