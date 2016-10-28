package ua.rd.pizzaservice.services;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.rd.pizzaservice.domain.LoyaltyCard;
import ua.rd.pizzaservice.repository.CardRepository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class SimpleCardServiceTest {

    private static CardService service;

    @BeforeClass
    public static void createService() {
        CardRepository cardRepository = mock(CardRepository.class);
        when(cardRepository.findById(0L)).thenReturn(new LoyaltyCard(100L));
        when(cardRepository.findById(1L)).thenReturn(new LoyaltyCard(0L));
        service = new SimpleCardService(cardRepository);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addToBalanceToNullCard() {
        service.addToBalance(null, 0L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addToBalanceNegativeValue() {
        service.addToBalance(new LoyaltyCard(100L), -3L);
    }

    @Test
    public void addToBalance() {
        LoyaltyCard card = new LoyaltyCard(100L);
        service.addToBalance(card, 5L);
        assertThat(card.getBalance(), is(105L));
    }
}