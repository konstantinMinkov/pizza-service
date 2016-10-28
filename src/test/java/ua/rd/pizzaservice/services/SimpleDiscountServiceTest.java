package ua.rd.pizzaservice.services;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.rd.pizzaservice.domain.discounts.Discount;
import ua.rd.pizzaservice.domain.discounts.LoyaltyCardDiscount;
import ua.rd.pizzaservice.domain.discounts.MostExpensivePizzaDiscount;

import java.util.Arrays;

import static org.junit.Assert.*;


public class SimpleDiscountServiceTest {

    private static DiscountService service;

    @BeforeClass
    private static void createService() {
        Discount cardDiscount = new LoyaltyCardDiscount(10, 30);
        Discount pizzaDiscount = new MostExpensivePizzaDiscount(30);
        service = new SimpleDiscountService(Arrays.asList(cardDiscount, pizzaDiscount));
    }

}