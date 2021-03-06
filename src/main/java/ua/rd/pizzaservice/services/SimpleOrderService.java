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
    private final CardService cardService;

    @Autowired
    public SimpleOrderService(OrderRepository orderRepository, PizzaService pizzaService,
                              DiscountService discountService, CardService cardService) {
        this.orderRepository = orderRepository;
        this.pizzaService = pizzaService;
        this.discountService = discountService;
        this.cardService = cardService;
    }

    @Override
    public Order placeNewOrder(Customer customer, Long ... pizzasId) {
        checkPizzasQuantity(pizzasId);
        checkCustomer(customer);

        List<Pizza> pizzas = findPizzasByIds(pizzasId);
        Order order = instantiateNewOrder();
        order.setCustomer(customer);
        order.setPizzas(pizzas);

        return saveOrder(order);
    }

    @Override
    public Long checkout(Order order) {
        final Long totalWithDiscounts = countTotalWithDiscounts(order);

        Customer customer = order.getCustomer();
        checkCustomer(customer);

        LoyaltyCard card = customer.getLoyaltyCard();
        if (card != null) {
            cardService.addToBalance(card, totalWithDiscounts);
        }

        return totalWithDiscounts;
    }

    private Long countTotalWithDiscounts(Order order) {
        final Long totalPrice = order.totalPrice();
        final Long discounts = discountService.countDiscounts(order);
        return totalPrice - discounts;
    }

    private void checkCustomer(Customer customer) {
        if (customer == null) throw new IllegalArgumentException("Customer is null.");
        if (customer.getAddress() == null) throw new IllegalArgumentException("Address is null");
    }

    private void checkPizzasQuantity(Long ... pizzasId) {
        if (tooManyPizzasInOrder(pizzasId)) throw new IllegalStateException("Too many pizzas in order.");
        if (noPizzasInOrder(pizzasId)) throw new IllegalStateException("No pizzas in order.");
    }

    private boolean noPizzasInOrder(Long ... pizzasId) {
        return pizzasId.length == 0;
    }

    private boolean tooManyPizzasInOrder(Long ... pizzasId) {
        return pizzasId.length > MAX_PIZZAS_IN_ORDER;
    }

    @Lookup
    protected Order instantiateNewOrder() {
        throw new IllegalStateException();
    }

    private List<Pizza> findPizzasByIds(Long ... ids) {
        List<Pizza> pizzas = new ArrayList<>();
        for (Long id : ids) {
            pizzas.add(findPizzaById(id));
        }
        return pizzas;
    }

    private Pizza findPizzaById(Long id) {
        return pizzaService.findById(id);
    }

    @Benchmark
    private Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
}
