package ua.rd.pizzaservice.repository;

import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.domain.PizzaType;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


//@Repository
public class InMemoryPizzaRepository implements PizzaRepository {

    private final List<Pizza> availablePizzas = new ArrayList<>();

    @PostConstruct
    public void init() {
        availablePizzas.add(new Pizza("Pizza 1", 240L, PizzaType.MEAT));
        availablePizzas.add(new Pizza("Pizza 2", 140L, PizzaType.SEA));
        availablePizzas.add(new Pizza("Pizza 3", 350L, PizzaType.VEGETARIAN));
        availablePizzas.add(new Pizza("Pizza 4", 120L, PizzaType.MEAT));
        availablePizzas.add(new Pizza("Pizza 5", 270L, PizzaType.SEA));
    }

    public void postCreate() {
        System.out.println("Called");
    }

//    @Benchmark
    @Override
    public Pizza findById(Integer id) {
        return availablePizzas.get(id);
    }

    @Override
    public Pizza save(Pizza pizza) {
        throw new UnsupportedOperationException();
    }
}
