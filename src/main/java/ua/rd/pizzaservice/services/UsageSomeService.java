package ua.rd.pizzaservice.services;

/**
 * Created by Kostiantyn_Minkov on 10/6/2016.
 */
public class UsageSomeService {

    private SomeService someService;

    public UsageSomeService(SomeService someService) {
        this.someService = someService;
    }

    public void init() {
        System.out.println(someService.getString());
    }
}
