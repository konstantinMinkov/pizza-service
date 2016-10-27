package ua.rd.pizzaservice.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.rd.pizzaservice.domain.LoyaltyCard;
import ua.rd.pizzaservice.repository.CardRepository;


@Service
public class SimpleCardService implements CardService {

    private CardRepository cardRepository;

    @Autowired
    public SimpleCardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public void addToBalance(LoyaltyCard card, long toAdd) {
        if (card == null) throw new IllegalArgumentException("Card is null.");
        card.setBalance(card.getBalance() + toAdd);
        cardRepository.save(card);
    }
}
