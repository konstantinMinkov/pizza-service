package ua.rd.pizzaservice.repository;

import ua.rd.pizzaservice.domain.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kostiantyn_Minkov on 10/4/2016.
 */
public class InMemoryOrderRepository implements OrderRepository {

    private List<Order> orders = new ArrayList<>();

    @Override
    public Order save(Order order) {
        orders.add(order);
        return order;
    }
}
