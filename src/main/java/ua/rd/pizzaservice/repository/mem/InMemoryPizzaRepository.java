package ua.rd.pizzaservice.repository.mem;

import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.domain.PizzaType;
import ua.rd.pizzaservice.repository.PizzaRepository;

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
    public Pizza findById(Long id) {
        return availablePizzas.get(id.intValue());
    }

    @Override
    public Pizza save(Pizza pizza) {
        throw new UnsupportedOperationException();
    }
}
