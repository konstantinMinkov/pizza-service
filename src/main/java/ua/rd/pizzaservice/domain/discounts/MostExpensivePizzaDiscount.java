package ua.rd.pizzaservice.domain.discounts;


import org.springframework.stereotype.Component;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.domain.Pizza;

import java.util.List;


public class MostExpensivePizzaDiscount implements Discount {

    private final long ONE_HUNDRED_PERCENTS = 100;
    private final long DISCOUNT_PERCENTS;

    public MostExpensivePizzaDiscount(long percents) {
        DISCOUNT_PERCENTS = percents;
    }

    @Override
    public Long apply(Order order) {
        final List<Pizza> pizzas = order.getPizzas();
        if (isMoreThenFourPizzas(pizzas)) {
            return countDiscount(pizzas);
        }
        return 0L;
    }

    private long countDiscount(List<Pizza> pizzas) {
        if (pizzas.isEmpty()) return 0L;
        return getTheMostExpensivePizza(pizzas).getPrice() * DISCOUNT_PERCENTS / ONE_HUNDRED_PERCENTS;
    }

    private Pizza getTheMostExpensivePizza(List<Pizza> pizzas) {
        Pizza theMostExpensive = pizzas.get(0);
        for (int i = 1; i < pizzas.size(); i++) {
            final Pizza currentPizza = pizzas.get(i);
            if (theMostExpensive.getPrice()
                    .compareTo(currentPizza.getPrice()) < 0) {
                theMostExpensive = currentPizza;
            }
        }
        return theMostExpensive;
    }

    private boolean isMoreThenFourPizzas(List<Pizza> pizzas) {
        return pizzas.size() > 4;
    }
}
