package ua.rd.pizzaservice.services;

import ua.rd.pizzaservice.domain.Pizza;


public interface PizzaService {

    Pizza findById(Long pizzaID);

    Pizza save(Pizza pizza);
}
