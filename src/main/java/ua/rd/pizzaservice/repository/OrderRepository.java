package ua.rd.pizzaservice.repository;

import ua.rd.pizzaservice.domain.Order;

/**
 * Created by Kostiantyn_Minkov on 10/4/2016.
 */
public interface OrderRepository {

    Order save(Order order);
}
