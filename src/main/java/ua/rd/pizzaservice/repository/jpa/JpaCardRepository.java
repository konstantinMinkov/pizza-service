package ua.rd.pizzaservice.repository.jpa;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.rd.pizzaservice.domain.LoyaltyCard;
import ua.rd.pizzaservice.repository.CardRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class JpaCardRepository implements CardRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public LoyaltyCard findById(Long id) {
        return entityManager.find(LoyaltyCard.class, id);
    }

    @Override
    @Transactional
    public LoyaltyCard save(LoyaltyCard card) {
        return entityManager.merge(card);
    }
}
