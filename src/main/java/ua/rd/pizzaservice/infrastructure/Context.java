package ua.rd.pizzaservice.infrastructure;

/**
 * Created by Kostiantyn_Minkov on 10/4/2016.
 */
public interface Context {

    <T> T getBean(String beanName);
}
