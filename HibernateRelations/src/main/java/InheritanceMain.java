import entities.inheritance.Bike;
import entities.inheritance.Car;
import entities.inheritance.Truck;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class InheritanceMain {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("relations");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();



        em.persist(new Car());
        em.persist(new Bike());
        em.persist(new Truck(10));

        Car car = em.find(Car.class, 1);
        Car car2 = em.find(Car.class, 2);


        em.getTransaction().commit();}
}
