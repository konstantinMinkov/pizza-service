package ua.rd.pizzaservice.domain;

import lombok.*;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@Data
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Order {

    private static AtomicLong lastId = new AtomicLong(-1);

    private final Long id = lastId.incrementAndGet();
    private Customer customer;
    private List<Pizza> pizzas;
    private OrderStatus orderStatus;

    public Order(Customer customer, List<Pizza> pizzas) {
        this(customer);
        this.pizzas = pizzas;
    }

    public Order(Customer customer) {
        this();
        this.customer = customer;
    }

    public Order() {
        this.orderStatus = OrderStatus.NEW;
    }

    public Long totalPrice() {
        if (pizzas == null) throw new IllegalStateException("No pizzas in order");
        Long total = 0L;
        for (Pizza pizza : pizzas) {
            total += pizza.getPrice();
        }
        return total;
    }

    public void changeStatus(OrderStatus status) {
        if ( !orderStatus.canChangeToStatus(status)) {
            throw new IllegalStateException("Can't change to such status.");
        }
        orderStatus = status;
    }
}
