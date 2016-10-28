package ua.rd.pizzaservice.repository;

import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
        final Pizza pizza = new Pizza(2L, "Margarita", 100L, PizzaType.SEA);
        jdbcTemplate.update("insert into Pizza values(?, ?, ?, ?)",
                pizza.getId(),
                pizza.getName(),
                pizza.getPrice(),
                pizza.getType().toString());
        assertThat(repository.findById(2L), is(pizza));
    }

    @Test
    public void save() {
        final Pizza pizza = new Pizza("Pizza", 100L, PizzaType.SEA);
        final Pizza savedPizza = savePizza(pizza);
        final Pizza dbPizza = jdbcTemplate.queryForObject(
                "select * from Pizza where id = ?",
                new Object[] { savedPizza.getId() },
                (rs, i) -> {
                    Pizza pizza1 = new Pizza();
                    pizza1.setId(rs.getLong("id"));
                    pizza1.setName(rs.getString("name"));
                    pizza1.setPrice(rs.getLong("price"));
                    pizza1.setType(PizzaType.valueOf(rs.getString("type")));
                    return pizza1;
                });

        String name = (String) jdbcTemplate.queryForObject(
                "select name from Pizza where name = 'Pizza'",
                String.class
        );
        System.out.println(name);

        assertThat(savedPizza.getId(), is(notNullValue()));
//        assertThat(savedPizza, is(dbPizza));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private Pizza savePizza(Pizza pizza) {
        return repository.save(pizza);
    }
}