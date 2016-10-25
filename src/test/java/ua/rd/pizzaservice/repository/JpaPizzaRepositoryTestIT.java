package ua.rd.pizzaservice.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.domain.PizzaType;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;


//@Transactional - уже есть в AbstractTransactionalJUnit4SpringContextTests
//@Rollback - убираем, потому, что у нас Н2, и откатывать изменения не надо
public class JpaPizzaRepositoryTestIT extends RepositoryTestConfig {

    @Autowired
    private PizzaRepository repository;

    @Test
    public void find() {
    }

    @Test
    public void save() {
        final Pizza pizza = new Pizza("Pizza", 100L, PizzaType.SEA);
        final Pizza savedPizza = repository.save(pizza);
        assertThat(savedPizza.getId(), is(notNullValue()));
    }
}