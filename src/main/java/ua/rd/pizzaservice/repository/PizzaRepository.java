package ua.rd.pizzaservice.repository;

import ua.rd.pizzaservice.domain.Pizza;

/**
 * Created by Kostiantyn_Minkov on 10/4/2016.
 */
public interface PizzaRepository {

    Pizza findById(int id);

}
