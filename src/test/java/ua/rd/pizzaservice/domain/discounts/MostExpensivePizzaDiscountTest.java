package ua.rd.pizzaservice.domain.discounts;

import org.junit.Before;
import org.junit.Test;
import ua.rd.pizzaservice.domain.*;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class MostExpensivePizzaDiscountTest {

    private final long DISCOUNT_PERCENTS = 30;

    private MostExpensivePizzaDiscount discount;
    private Order order;

    @Before
    public void setUp() {
        discount = new MostExpensivePizzaDiscount(DISCOUNT_PERCENTS);
        order = new Order();
    }

    @Test
    public void testThreePizzasOrder() {
        createPresetOrder(
                new Pizza(0, "Pizza 1", 240L, PizzaType.MEAT),
                new Pizza(1, "Pizza 2", 140L, PizzaType.SEA),
                new Pizza(2, "Pizza 3", 350L, PizzaType.VEGETARIAN)
        );
        assertThat(discount.apply(order), is(0L));
    }

    @Test
    public void testFourPizzasOrder() {
        createPresetOrder(
                new Pizza(0, "Pizza 1", 240L, PizzaType.MEAT),
                new Pizza(1, "Pizza 2", 140L, PizzaType.SEA),
                new Pizza(2, "Pizza 3", 350L, PizzaType.VEGETARIAN),
                new Pizza(3, "Pizza 4", 120L, PizzaType.MEAT)
        );
        assertThat(discount.apply(order), is(0L));
    }

    @Test
    public void testFivePizzasOrder() {
        createPresetOrder(
                new Pizza(0, "Pizza 1", 240L, PizzaType.MEAT),
                new Pizza(1, "Pizza 2", 140L, PizzaType.SEA),
                new Pizza(2, "Pizza 3", 300L, PizzaType.VEGETARIAN),
                new Pizza(3, "Pizza 4", 120L, PizzaType.MEAT),
                new Pizza(3, "Pizza 4", 120L, PizzaType.MEAT)
        );
        assertThat(discount.apply(order), is(90L));
    }

    @Test
    public void testNoPizzasOrder() {
        createPresetOrder();
        assertThat(discount.apply(order), is(0L));
    }

    public void createPresetOrder(Pizza ... pizzas) {
        order.setCustomer(createPresetCustomer());
        order.setPizzas(Arrays.asList(pizzas));
    }

    public Customer createPresetCustomer() {
        final LoyaltyCard loyaltyCard = new LoyaltyCard(0L);
        return new Customer("John", new Address("City, Road, number"), loyaltyCard);
    }
}