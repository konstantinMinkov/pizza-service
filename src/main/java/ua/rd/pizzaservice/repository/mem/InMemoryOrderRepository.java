package ua.rd.pizzaservice.repository.mem;

import org.springframework.stereotype.Repository;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;


//@Repository
public class InMemoryOrderRepository implements OrderRepository {

    private List<Order> orders = new ArrayList<>();

    @Override
    public Order save(Order order) {
        orders.add(order);
        return order;
    }
}
