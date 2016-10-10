package ua.rd.pizzaservice.services;

/**
 * Created by Kostiantyn_Minkov on 10/6/2016.
 */
public class TestSomeService1 implements SomeService {

    @Override
    public String getString() {
        return "Test1";
    }

    public void destroy() {
        System.out.println("destroy");
    }
}
