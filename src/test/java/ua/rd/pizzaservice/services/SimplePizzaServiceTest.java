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
        service = new SimplePizzaService(repository);
    }

    @Test
    public void testFindById() throws Exception {
        assertThat(service.findById(0L), is(new Pizza(0L, "Pizza 1", 240L, PizzaType.MEAT)));
        assertThat(service.findById(3L), is(nullValue()));
    }
}