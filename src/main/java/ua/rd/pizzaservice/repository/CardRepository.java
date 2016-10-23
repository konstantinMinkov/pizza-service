package ua.rd.pizzaservice.repository;


import ua.rd.pizzaservice.domain.LoyaltyCard;


public interface CardRepository {

    void saveCard(LoyaltyCard card);
    LoyaltyCard findCardById(Long id);
}
