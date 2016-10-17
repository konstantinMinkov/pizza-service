package ua.rd.pizzaservice.domain.discounts;


import ua.rd.pizzaservice.domain.Order;


public interface Discount {

    Long apply(Order order);
}
