package ua.rd.pizzaservice.services;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.domain.PizzaType;
import ua.rd.pizzaservice.repository.InMemoryPizzaRepository;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class SimplePizzaServiceTest {

    private static SimplePizzaService service;

    @BeforeClass
    public static void createService() {
        final InMemoryPizzaRepository repository = mock(InMemoryPizzaRepository.class);
        when(repository.findById(0)).thenReturn(new Pizza(0, "Pizza 1", 240L, PizzaType.MEAT));
        when(repository.findById(1)).thenReturn(new Pizza(1, "Pizza 2", 140L, PizzaType.SEA));
        when(repository.findById(2)).thenReturn(new Pizza(2, "Pizza 3", 350L, PizzaType.VEGETARIAN));
        service = new SimplePizzaService(repository);
    }

    @Test
    public void testFindById() throws Exception {
        assertThat(service.findById(0), is(new Pizza(0, "Pizza 1", 240L, PizzaType.MEAT)));
        assertThat(service.findById(1), is(new Pizza(1, "Pizza 2", 140L, PizzaType.SEA)));
        assertThat(service.findById(2), is(new Pizza(2, "Pizza 3", 350L, PizzaType.VEGETARIAN)));
        assertThat(service.findById(3), is(nullValue()));
    }
}