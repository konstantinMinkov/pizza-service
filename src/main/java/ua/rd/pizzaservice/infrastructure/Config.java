package ua.rd.pizzaservice.infrastructure;

/**
 * Created by Kostiantyn_Minkov on 10/4/2016.
 */
public interface Config {

    Class<?> getImpl(String name);
}
