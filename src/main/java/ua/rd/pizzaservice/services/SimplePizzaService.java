package ua.rd.pizzaservice.services;

import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.infrastructure.InitialContext;
import ua.rd.pizzaservice.repository.InMemoryPizzaRepository;
import ua.rd.pizzaservice.repository.PizzaRepository;

/**
 * Created by Kostiantyn_Minkov on 10/4/2016.
 */
public class SimplePizzaService implements PizzaService {

    private PizzaRepository pizzaRepository;

    public SimplePizzaService(PizzaRepository pizzaRepository) {
//        InitialContext initialContext = new InitialContext();
//        pizzaRepository = initialContext.getInstance("pizzaRepository");
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public Pizza findById(Integer pizzaId) {
        return pizzaRepository.findById(pizzaId);
    }
}
