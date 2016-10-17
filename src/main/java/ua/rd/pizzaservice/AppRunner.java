package ua.rd.pizzaservice;

import ua.rd.pizzaservice.domain.Customer;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.infrastructure.ApplicationContext;
import ua.rd.pizzaservice.infrastructure.Benchmark;
import ua.rd.pizzaservice.infrastructure.Context;
import ua.rd.pizzaservice.infrastructure.JavaConfig;
import ua.rd.pizzaservice.services.SimpleOrderService;


public class AppRunner {

    public static void main(String[] args) {
        Customer customer = null;
        Order order;

        Context context = new ApplicationContext(new JavaConfig());
        SimpleOrderService orderService = context.getBean("orderService");
        order = orderService.placeNewOrder(customer, 1, 2, 3);

        System.out.println(order);
    }
}
