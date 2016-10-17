package ua.rd.pizzaservice.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.domain.discounts.Discount;
import ua.rd.pizzaservice.domain.discounts.LoyaltyCardDiscount;
import ua.rd.pizzaservice.domain.discounts.MostExpensivePizzaDiscount;

import java.util.ArrayList;
import java.util.List;


@Service
public class DiscountService {

    private List<Discount> discounts;

    public DiscountService(List<Discount> discounts) {
        this.discounts = discounts;
    }

    public Long countDiscounts(Order order) {
        long totalDiscount = 0;
        for (Discount discount : discounts) {
            totalDiscount += discount.apply(order);
        }
        return totalDiscount;
    }
}