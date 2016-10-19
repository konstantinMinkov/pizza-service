package ua.rd.pizzaservice.infrastructure.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.domain.discounts.Discount;
import ua.rd.pizzaservice.domain.discounts.LoyaltyCardDiscount;
import ua.rd.pizzaservice.domain.discounts.MostExpensivePizzaDiscount;
import ua.rd.pizzaservice.repository.PizzaRepository;
import ua.rd.pizzaservice.services.DiscountService;
import ua.rd.pizzaservice.services.PizzaService;

import java.util.Arrays;


@Configuration
@ComponentScan(basePackageClasses = {PizzaService.class, Order.class, Discount.class})
@Import(RepositoryConfig.class)
public class ApplicationConfig {

    @Bean
    public LoyaltyCardDiscount loyaltyCardDiscount() {
        return new LoyaltyCardDiscount(10, 30);
    }

    @Bean
    public MostExpensivePizzaDiscount mostExpensivePizzaDiscount() {
        return new MostExpensivePizzaDiscount(30);
    }

    @Bean
    public DiscountService discountService() {
        return new DiscountService(
                Arrays.asList(loyaltyCardDiscount(), mostExpensivePizzaDiscount()));
    }
}
