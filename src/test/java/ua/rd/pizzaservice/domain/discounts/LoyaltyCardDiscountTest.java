package ua.rd.pizzaservice.domain.discounts;

import org.junit.Before;
import org.junit.Test;
import ua.rd.pizzaservice.domain.*;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by Konstantin Minkov on 15.10.2016.
 */
public class LoyaltyCardDiscountTest {

    private final long DISCOUNT_PERCENTAGE = 10;
    private final long TOTAL_PRICE_PERCENTAGE = 30;

    private LoyaltyCardDiscount discount;
    private Order order;

    @Before
    public void setUp() throws Exception {
        discount = new LoyaltyCardDiscount(DISCOUNT_PERCENTAGE, TOTAL_PRICE_PERCENTAGE);
        order = new Order();
    }

    @Test
    public void testLessMoneyOnCard() {
        createPresetOrder(new LoyaltyCard(20L));
        assertThat(discount.apply(order), is(2L));
    }

    @Test
    public void testMoreMoneyOnCard() {
        createPresetOrder(new LoyaltyCard(5000L));
        assertThat(discount.apply(order), is(120L));
    }

    @Test
    public void testWithNoCard() {
        createPresetOrder(null);
        assertThat(discount.apply(order), is(0L));
    }

    @Test
    public void testWithEmptyCard() {
        createPresetOrder(new LoyaltyCard(0L));
        assertThat(discount.apply(order), is(0L));
    }

    public void createPresetOrder(LoyaltyCard loyaltyCard) {
        order.setCustomer(new Customer("John", new Address("City, Road, number"), loyaltyCard));
        order.setPizzas(Arrays.asList(
                new Pizza(1, "Pizza name", 100L, PizzaType.MEAT),
                new Pizza(1, "Pizza name", 100L, PizzaType.VEGETARIAN),
                new Pizza(1, "Pizza name", 100L, PizzaType.SEA),
                new Pizza(1, "Pizza name", 100L, PizzaType.MEAT)
        ));
    }
}