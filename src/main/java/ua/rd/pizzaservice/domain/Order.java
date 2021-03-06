package ua.rd.pizzaservice.domain;

import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;


@Data
@Entity(name = "orders")
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Order {

    @TableGenerator(name="order_gen")
    @Id @GeneratedValue(generator="order_gen")
    private Long id;
    @ManyToOne
    @Cascade({CascadeType.PERSIST, CascadeType.MERGE})
    private Customer customer;

    @ElementCollection
    @CollectionTable(name = "orders_to_pizzas")
    @MapKeyJoinColumn(name = "pizza_id")
    @Column(name = "quantity")
    @Cascade({CascadeType.PERSIST, CascadeType.MERGE})
    private Map<Pizza, Long> pizzas;
    private OrderStatus orderStatus;

    public Order(Customer customer, List<Pizza> pizzas) {
        this(customer);
        setPizzas(pizzas);
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
        for (Pizza pizza : pizzas.keySet()) {
            total += pizza.getPrice() * pizzas.get(pizza);
        }
        return total;
    }

    public void changeStatus(OrderStatus status) {
        if ( !orderStatus.canChangeToStatus(status)) {
            throw new IllegalStateException("Can't change to such status.");
        }
        orderStatus = status;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas.stream().collect(
                Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public List<Pizza> getPizzas() {
        List<Pizza> pizzasList = new ArrayList<>();
        for (Pizza pizza : pizzas.keySet()) {
            for (int i = 0; i < pizzas.get(pizza); i++) {
                pizzasList.add(pizza);
            }
        }
        return pizzasList;
    }
}
