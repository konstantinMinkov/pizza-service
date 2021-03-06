package ua.rd.pizzaservice.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ua.rd.pizzaservice.services.PizzaService;
import ua.rd.pizzaservice.web.infrastructure.MyController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller("/hello")
public class HelloController implements MyController {

    @Autowired
    private PizzaService pizzaService;

    @Override
    public void handleRequest(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.getOutputStream().print("Hello from Hello.");
            resp.getOutputStream().print(pizzaService.findById(1L).toString());
        } catch (IOException ignored) {}
    }
}
