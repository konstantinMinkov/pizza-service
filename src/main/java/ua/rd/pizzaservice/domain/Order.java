package ua.rd.pizzaservice.domain;

import lombok.Data;

import java.util.List;

/**
 * Created by Kostiantyn_Minkov on 10/4/2016.
 */

@Data
public class Order {

    private static Long lastId = -1L;
    private Long id;
    private Customer customer;
    private List<Pizza> pizzas;

    public Order(Customer customer, List<Pizza> pizzas) {
        this.id = ++lastId;
        this.customer = customer;
        this.pizzas = pizzas;
    }
}
