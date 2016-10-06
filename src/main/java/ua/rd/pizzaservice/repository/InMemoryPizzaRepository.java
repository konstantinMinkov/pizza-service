package ua.rd.pizzaservice.repository;

import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.domain.PizzaType;
import ua.rd.pizzaservice.infrastructure.Benchmark;
import ua.rd.pizzaservice.infrastructure.PostCreate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kostiantyn_Minkov on 10/4/2016.
 */
public class InMemoryPizzaRepository implements PizzaRepository {

    private final List<Pizza> availablePizzas = new ArrayList<>();

    public void init() {
        availablePizzas.add(new Pizza(0, "Pizza 1", BigDecimal.valueOf(2.40), PizzaType.MEAT));
        availablePizzas.add(new Pizza(1, "Pizza 2", new BigDecimal(1.40), PizzaType.SEA));
        availablePizzas.add(new Pizza(2, "Pizza 3", new BigDecimal(3.50), PizzaType.VEGETARIAN));
        availablePizzas.add(new Pizza(3, "Pizza 4", new BigDecimal(1.20), PizzaType.MEAT));
        availablePizzas.add(new Pizza(4, "Pizza 5", new BigDecimal(2.70), PizzaType.SEA));
    }

    @PostCreate
    public void postCreate() {
        System.out.println("Called");
    }

    @Benchmark
    @Override
    public Pizza findById(int id) {
        return availablePizzas.get(id);
    }
}
