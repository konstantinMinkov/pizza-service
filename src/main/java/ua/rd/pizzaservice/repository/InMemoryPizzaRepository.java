package ua.rd.pizzaservice.repository;

import org.springframework.stereotype.Repository;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.domain.PizzaType;
import ua.rd.pizzaservice.infrastructure.Benchmark;
import ua.rd.pizzaservice.infrastructure.PostCreate;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Repository
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
    public Pizza findById(int id) {
        return availablePizzas.get(id);
    }
}
