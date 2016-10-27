package ua.rd.pizzaservice.repository;


import ua.rd.pizzaservice.domain.LoyaltyCard;


public interface CardRepository {

    LoyaltyCard findById(Long id);
    LoyaltyCard save(LoyaltyCard card);
}
