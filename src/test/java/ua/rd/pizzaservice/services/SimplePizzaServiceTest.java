package ua.rd.pizzaservice.services;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.domain.PizzaType;
import ua.rd.pizzaservice.repository.mem.InMemoryPizzaRepository;

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
        when(repository.findById(0L)).thenReturn(new Pizza(0L, "Pizza 1", 240L, PizzaType.MEAT));
        when(repository.findById(1L)).thenReturn(new Pizza(1L, "Pizza 2", 140L, PizzaType.SEA));
        when(repository.findById(2L)).thenReturn(new Pizza(2L, "Pizza 3", 350L, PizzaType.VEGETARIAN));
        service = new SimplePizzaService(repository);
    }

    @Test
    public void testFindById() throws Exception {
        assertThat(service.findById(0L), is(new Pizza(0L, "Pizza 1", 240L, PizzaType.MEAT)));
        assertThat(service.findById(1L), is(new Pizza(1L, "Pizza 2", 140L, PizzaType.SEA)));
        assertThat(service.findById(2L), is(new Pizza(2L, "Pizza 3", 350L, PizzaType.VEGETARIAN)));
        assertThat(service.findById(3L), is(nullValue()));
    }
}