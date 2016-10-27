package ua.rd.pizzaservice.infrastructure;

import ua.rd.pizzaservice.repository.mem.InMemoryOrderRepository;
import ua.rd.pizzaservice.repository.mem.InMemoryPizzaRepository;
import ua.rd.pizzaservice.services.SimpleOrderService;
import ua.rd.pizzaservice.services.SimplePizzaService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kostiantyn_Minkov on 10/4/2016.
 */

public class JavaConfig implements Config {

    private Map<String, Class<?>> classes = new HashMap<>();

    {
        classes.put("pizzaRepository", InMemoryPizzaRepository.class);
        classes.put("orderRepository", InMemoryOrderRepository.class);
        classes.put("pizzaService", SimplePizzaService.class);
        classes.put("orderService", SimpleOrderService.class);
    }

    @Override
    public Class<?> getImpl(String name) {
        return classes.get(name);
    }
}
