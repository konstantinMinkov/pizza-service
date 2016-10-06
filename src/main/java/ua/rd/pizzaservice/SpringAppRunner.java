package ua.rd.pizzaservice;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.rd.pizzaservice.repository.PizzaRepository;
import ua.rd.pizzaservice.services.OrderService;

import java.util.Arrays;

/**
 * Created by Kostiantyn_Minkov on 10/6/2016.
 */
public class SpringAppRunner {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext("appContext.xml");

        PizzaRepository repository = (PizzaRepository) context.getBean("pizzaRepository");
        System.out.println(repository.findById(1));
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));

        OrderService orderService = (OrderService) context.getBean("orderService");
        System.out.println(orderService.placeNewOrder(null, 1, 2, 3));

        context.close();
    }
}
