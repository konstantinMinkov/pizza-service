package ua.rd.pizzaservice;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.rd.pizzaservice.services.OrderService;
import ua.rd.pizzaservice.services.SomeService;

import java.util.Arrays;

/**
 * Created by Kostiantyn_Minkov on 10/6/2016.
 */
public class SpringAppRunner {

    public static void main(String[] args) {
        ConfigurableApplicationContext repoContext =
                new ClassPathXmlApplicationContext("repoContext.xml");
        System.out.println(Arrays.toString(repoContext.getBeanDefinitionNames()));

        ConfigurableApplicationContext appContext =
                new ClassPathXmlApplicationContext(new String[]{"appContext.xml"}, repoContext);
        System.out.println(Arrays.toString(appContext.getBeanDefinitionNames()));


//        System.out.println(repoContext.getBean("service1", SomeService.class).getString());
//        System.out.println(appContext.getBean("service1", SomeService.class).getString());

        System.out.println(appContext.getBean("orderService", OrderService.class)
                .placeNewOrder(null, 1, 2, 3));

        repoContext.close();
        appContext.close();
    }
}
