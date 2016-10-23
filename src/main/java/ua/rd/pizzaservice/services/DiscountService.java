package ua.rd.pizzaservice.services;


import ua.rd.pizzaservice.domain.Order;


public interface DiscountService {

    Long countDiscounts(Order order);
}
