package ua.rd.pizzaservice.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.services.PizzaService;


@RestController
public class PizzaRestController {

    private PizzaService pizzaService;

    @Autowired
    public PizzaRestController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @RequestMapping("/hello/{id}")
    public Pizza getPizza(@PathVariable("id") Long id) {
        return pizzaService.findById(id);
    }
}
