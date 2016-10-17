package ua.rd.pizzaservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import ua.rd.pizzaservice.domain.Customer;
import ua.rd.pizzaservice.domain.LoyaltyCard;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.infrastructure.Benchmark;
import ua.rd.pizzaservice.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;


@Service("orderService")
public class SimpleOrderService implements OrderService {

    private static final int MAX_PIZZAS_IN_ORDER = 10;
    private final OrderRepository orderRepository;
    private final PizzaService pizzaService;
    private final DiscountService discountService;

    @Autowired
    public SimpleOrderService(OrderRepository orderRepository, PizzaService pizzaService,
                              DiscountService discountService) {
        this.orderRepository = orderRepository;
        this.pizzaService = pizzaService;
        this.discountService = discountService;
    }

    @Override
    public Order placeNewOrder(Customer customer, Integer ... pizzasId) {
        checkPizzasQuantity(pizzasId);
        checkCustomer(customer);

        List<Pizza> pizzas = findPizzasByIds(pizzasId);
        Order newOrder = instantiateNewOrder();
        newOrder.setCustomer(customer);
        newOrder.setPizzas(pizzas);

        Long totalPrice = newOrder.totalPrice();
        Long discounts = discountService.countDiscounts(newOrder);
        Long totalWithDiscounts = totalPrice - discounts;
        
        // TODO: 15.10.2016 
        // 1. how to manage with total?
        // 2. save it

        LoyaltyCard card = customer.getLoyaltyCard();
        if (card != null) {
            card.setBalance(card.getBalance() + totalPrice);
            //// TODO: 15.10.2016 save card somewhere
        }
        
        saveOrder(newOrder);
        return newOrder;
    }

    private void checkCustomer(Customer customer) {
        if (customer == null) throw new IllegalArgumentException("Customer is null.");
        if (customer.getAddress() == null) throw new IllegalArgumentException("Address is null");
    }

    private void checkPizzasQuantity(Integer ... pizzasId) {
        if (tooManyPizzasInOrder(pizzasId)) throw new IllegalStateException("Too many pizzas in order.");
        if (noPizzasInOrder(pizzasId)) throw new IllegalStateException("No pizzas in order.");
    }

    private boolean noPizzasInOrder(Integer ... pizzasId) {
        return pizzasId.length == 0;
    }

    private boolean tooManyPizzasInOrder(Integer ... pizzasId) {
        return pizzasId.length > MAX_PIZZAS_IN_ORDER;
    }

    @Lookup
    protected Order instantiateNewOrder() {
        throw new IllegalStateException();
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

    @Benchmark
    private void saveOrder(Order order) {
        orderRepository.save(order);
    }
}
