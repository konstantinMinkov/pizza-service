package ua.rd.pizzaservice.repository.jpa;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.repository.OrderRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class JpaOrderRepository implements OrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Order save(Order order) {
        return entityManager.merge(order);
    }
}
