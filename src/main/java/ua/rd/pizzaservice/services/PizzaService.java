package ua.rd.pizzaservice.services;

import ua.rd.pizzaservice.domain.Pizza;

/**
 * Created by Kostiantyn_Minkov on 10/4/2016.
 */
public interface PizzaService {

    Pizza findById(Integer pizzaID);
}
