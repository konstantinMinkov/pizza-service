package ua.rd.pizzaservice;

import org.junit.Before;
import org.junit.Test;
import ua.rd.pizzaservice.domain.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class OrderTest {

    private Order order;

    @Before
    public void createOrder() {
        final Customer customer = new Customer();
        order = new Order(customer);
    }

    @Test(expected = IllegalStateException.class)
    public void testTotalOnNullPizzas() {
        order.totalPrice();
    }

    @Test
    public void testEmptyTotal() {
        fillWithPizzas(0);
        assertThat(order.totalPrice(), is(0L));
    }

    @Test
    public void testFourTotal() {
        fillWithPizzas(4);
        assertThat(order.totalPrice(), is(400L));
    }

    @Test
    public void testChangeStatus() {
        order.changeStatus(OrderStatus.IN_PROGRESS);
        order.changeStatus(OrderStatus.DONE);
    }

    @Test
    public void testCancelNewOrder() {
        order.changeStatus(OrderStatus.CANCELED);
    }

    @Test(expected = IllegalStateException.class)
    public void testCancelInProgressOrder() {
        order.changeStatus(OrderStatus.IN_PROGRESS);
        order.changeStatus(OrderStatus.CANCELED);
    }

    @Test(expected = IllegalStateException.class)
    public void testUnsupportedChangeStatus() {
        order.changeStatus(OrderStatus.CANCELED);
        order.changeStatus(OrderStatus.NEW);
    }

    private void fillWithPizzas(int quantity) {
        List<Pizza> pizzas = new ArrayList<>(quantity);
        for (int i = 0; i < quantity; i++) {
            pizzas.add(new Pizza((long) i, "Pizza name " + i, 100L, PizzaType.MEAT));
        }
        order.setPizzas(pizzas);
    }
}