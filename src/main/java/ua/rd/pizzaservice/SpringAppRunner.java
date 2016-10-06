package ua.rd.pizzaservice;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.rd.pizzaservice.repository.PizzaRepository;
import ua.rd.pizzaservice.services.OrderService;
import ua.rd.pizzaservice.services.SomeService;

import java.util.Arrays;

/**
 * Created by Kostiantyn_Minkov on 10/6/2016.
 */
public class SpringAppRunner {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext("appContext.xml");
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));

        ConfigurableApplicationContext context1 =
                new ClassPathXmlApplicationContext(new String[]{"appContext2.xml"}, context);
        System.out.println(Arrays.toString(context1.getBeanDefinitionNames()));
//        System.out.println(context.getBean("service1", SomeService.class).getString());
//        PizzaRepository repository = (PizzaRepository) context.getBean("pizzaRepository");
//        System.out.println(repository.findById(1));
//        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));

//        OrderService orderService = (OrderService) context.getBean("orderService");
//        System.out.println(orderService.placeNewOrder(null, 1, 2, 3));

        System.out.println(context1.getBean("service1", SomeService.class).getString());
        System.out.println(context.getBean("service1", SomeService.class).getString());

        context.close();
    }
}
