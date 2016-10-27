package ua.rd.pizzaservice.repository.jpa;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.repository.PizzaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class JpaPizzaRepository implements PizzaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Pizza findById(Long id) {
        return entityManager.find(Pizza.class, id);
    }

    @Override
    @Transactional
    public Pizza save(Pizza pizza) {
        return entityManager.merge(pizza);
    }
}
