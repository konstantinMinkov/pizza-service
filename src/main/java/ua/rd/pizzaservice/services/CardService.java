package ua.rd.pizzaservice.services;


import ua.rd.pizzaservice.domain.LoyaltyCard;


public interface CardService {

    void addToBalance(LoyaltyCard card, long toAdd);
}
