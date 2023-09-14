package org.example;

import entities.Courses;
import entities.Department;
import entities.User;
import orm.config.Connector;
import orm.config.EntityManager;
import orm.config.annotations.Entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException {
        Connector.createConnection("root", "12345", "soft_uni");

        Connection connection = Connector.getConnection();

        EntityManager<User> userEntityManager = new EntityManager<>(connection);
        boolean persistResult = userEntityManager.persist(new User("u", "p", 12, LocalDate.now()));

        System.out.println(persistResult);

      EntityManager<Courses> courseEntityManager = new EntityManager<>(connection);
   courseEntityManager.persist(new Courses("Math" , 12));
    }

}