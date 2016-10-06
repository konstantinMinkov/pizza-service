package ua.rd.pizzaservice.services;

import ua.rd.pizzaservice.domain.Customer;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.repository.InMemoryOrderRepository;
import ua.rd.pizzaservice.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kostiantyn_Minkov on 10/4/2016.
 */
public class SimpleOrderService implements OrderService {

    private final OrderRepository orderRepository;
    private final PizzaService pizzaService;

    public SimpleOrderService(OrderRepository orderRepository, PizzaService pizzaService) {
        this.orderRepository = orderRepository;
        this.pizzaService = pizzaService;
    }

    public Order placeNewOrder(Customer customer, Integer ... pizzasId) {
        List<Pizza> pizzas = findPizzasByIds(pizzasId);
        Order newOrder = new Order(customer, pizzas);
        saveOrder(newOrder);
        return newOrder;
    }

    private List<Pizza> findPizzasByIds(Integer ... ids) {
        List<Pizza> pizzas = new ArrayList<>();
        for (Integer id : ids) {
            pizzas.add(findPizzaById(id));
        }
        return pizzas;
    }

    private Pizza findPizzaById(Integer id) {
        return pizzaService.findById(id);
    }

    private void saveOrder(Order order) {
        orderRepository.save(order);
    }
}
