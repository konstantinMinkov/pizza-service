package ua.rd.pizzaservice.repository.mem;


import org.springframework.stereotype.Repository;
import ua.rd.pizzaservice.domain.LoyaltyCard;
import ua.rd.pizzaservice.repository.CardRepository;

import java.util.ArrayList;
import java.util.List;


//@Repository
public class InMemoryCardRepository implements CardRepository {

    private List<LoyaltyCard> cards = new ArrayList<>();

    @Override
    public LoyaltyCard save(LoyaltyCard card) {
        LoyaltyCard existingCard = findById(card.getId());
        if (existingCard != null) {
            cards.remove(existingCard);
        }
        cards.add(card);
        return card;
    }

    @Override
    public LoyaltyCard findById(Long id) {
        for (LoyaltyCard card : cards) {
            if (card.getId().equals(id)) {
                return card;
            }
        }
        return null;
    }
}
