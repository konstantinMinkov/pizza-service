package ua.rd.pizzaservice;


import lombok.val;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.domain.PizzaType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaAppRunner {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa");
        EntityManager manager = factory.createEntityManager();

        Pizza pizza = new Pizza("Margarita", 123L, PizzaType.VEGETARIAN);

        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();
        manager.persist(pizza);
        transaction.commit();
        manager.close();
        factory.close();
    }
}
