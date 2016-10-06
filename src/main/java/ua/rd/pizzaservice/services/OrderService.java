package ua.rd.pizzaservice.services;

import ua.rd.pizzaservice.domain.Customer;
import ua.rd.pizzaservice.domain.Order;

/**
 * Created by Kostiantyn_Minkov on 10/4/2016.
 */
public interface OrderService {

    Order placeNewOrder(Customer customer, Integer ... pizzasID);
}
