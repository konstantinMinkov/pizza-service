package ua.rd.pizzaservice.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.services.PizzaService;


@RestController
public class PizzaRestController {

    private PizzaService pizzaService;

    @Autowired
    public PizzaRestController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @RequestMapping(value = "/pizza/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pizza> find(@PathVariable("id") Long id) {
        final Pizza pizza = pizzaService.findById(id);
        if (pizza == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pizza, HttpStatus.FOUND);
    }

    @RequestMapping(value = "/pizza", method = RequestMethod.POST)
    public ResponseEntity<Pizza> save(@RequestBody Pizza pizza,
                                      UriComponentsBuilder builder) {
        Pizza p = pizzaService.save(pizza);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/pizza/{id}")
                        .buildAndExpand(p.getId())
                        .toUri()
        );

        return new ResponseEntity<Pizza>(headers, HttpStatus.CREATED);
    }
}
