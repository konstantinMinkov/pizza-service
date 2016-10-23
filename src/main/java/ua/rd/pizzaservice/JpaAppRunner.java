package ua.rd.pizzaservice;


import lombok.val;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.domain.PizzaType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Arrays;

public class JpaAppRunner {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa");
        EntityManager manager = factory.createEntityManager();

        Pizza pizza = new Pizza("Margarita", 123L, PizzaType.VEGETARIAN);

        Order order = new Order();
        order.setPizzas(Arrays.asList(pizza));

        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();
        manager.persist(order);
        transaction.commit();
        manager.close();
        factory.close();
    }
}
