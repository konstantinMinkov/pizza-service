package ua.rd.pizzaservice;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.rd.pizzaservice.domain.Address;
import ua.rd.pizzaservice.domain.Customer;
import ua.rd.pizzaservice.config.ApplicationConfig;
import ua.rd.pizzaservice.config.RepositoryConfig;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.domain.PizzaType;
import ua.rd.pizzaservice.repository.PizzaRepository;
import ua.rd.pizzaservice.services.OrderService;

import java.util.Arrays;


public class SpringAppRunner {

    public static void main(String[] args) {
//        ConfigurableApplicationContext repoContext =
//                new ClassPathXmlApplicationContext("repoContext.xml");
        ConfigurableApplicationContext repoContext =
                new AnnotationConfigApplicationContext(RepositoryConfig.class);
        System.out.println("Repo" + Arrays.toString(repoContext.getBeanDefinitionNames()));

//        ConfigurableApplicationContext appContext =
//                new ClassPathXmlApplicationContext(new String[]{"appContext.xml"}, repoContext);
        ConfigurableApplicationContext appContext =
                new AnnotationConfigApplicationContext(ApplicationConfig.class);

        System.out.println(Arrays.toString(appContext.getBeanDefinitionNames()));

        System.out.println(appContext.getBean("orderService", OrderService.class)
                .placeNewOrder( new Customer("John", new Address("City, Road, number"), null), 1L, 52L, 102L));

//        Pizza pizza = new Pizza("pizza", 10L, PizzaType.SEA);

//        pizza = appContext.getBean("jpaPizzaRepository", PizzaRepository.class).save(pizza);

//        System.out.println(pizza);

        repoContext.close();
        appContext.close();
    }
}
