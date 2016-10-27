package ua.rd.pizzaservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.infrastructure.Benchmark;
import ua.rd.pizzaservice.repository.PizzaRepository;


@Service
public class SimplePizzaService implements PizzaService {

    private PizzaRepository pizzaRepository;

    @Autowired
    public SimplePizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    @Benchmark
    public Pizza findById(Long pizzaId) {
        return pizzaRepository.findById(pizzaId);
    }
}
