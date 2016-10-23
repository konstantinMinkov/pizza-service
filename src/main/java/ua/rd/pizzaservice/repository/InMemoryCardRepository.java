package ua.rd.pizzaservice.repository;


import org.springframework.stereotype.Repository;
import ua.rd.pizzaservice.domain.LoyaltyCard;

import java.util.ArrayList;
import java.util.List;


@Repository
public class InMemoryCardRepository implements CardRepository {

    private List<LoyaltyCard> cards = new ArrayList<>();

    @Override
    public void saveCard(LoyaltyCard card) {
        LoyaltyCard existingCard = findCardById(card.getId());
        if (existingCard != null) {
            cards.remove(existingCard);
        }
        cards.add(card);
    }

    @Override
    public LoyaltyCard findCardById(Long id) {
        for (LoyaltyCard card : cards) {
            if (card.getId().equals(id)) {
                return card;
            }
        }
        return null;
    }
}
