package ua.rd.pizzaservice.repository;

import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.domain.PizzaType;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;


//@Transactional - уже есть в AbstractTransactionalJUnit4SpringContextTests
//@Rollback - убираем, потому, что у нас Н2, и откатывать изменения не надо
public class JpaPizzaRepositoryTestIT extends RepositoryTestConfig {

    @Autowired
    private PizzaRepository repository;

    @After
    public void afterTest() {
        jdbcTemplate.update("delete from Pizza");
    }

    @Test
    public void find() {
//        Pizza pizza = new Pizza(2L, )
//        jdbcTemplate.update()
    }

    @Test
    public void save() {
        final Pizza pizza = new Pizza("Pizza", 100L, PizzaType.SEA);
        final Pizza savedPizza = repository.save(pizza);
        assertThat(savedPizza.getId(), is(notNullValue()));
    }
}