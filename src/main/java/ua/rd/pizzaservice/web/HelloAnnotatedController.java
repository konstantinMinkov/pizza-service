package ua.rd.pizzaservice.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.services.PizzaService;
import ua.rd.pizzaservice.web.infrastructure.CustomRequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class HelloAnnotatedController {

    private PizzaService pizzaService;

    public HelloAnnotatedController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @CustomRequestMapping(path = "/hello/annotated")
    public void getPizza(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write(
                pizzaService.findById(1L).toString());
    }

    @CustomRequestMapping(path = "/other/route")
    public void printString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write("Random string.");
    }
}
