package ua.rd.pizzaservice.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@NoArgsConstructor
@Entity
public class LoyaltyCard {

    @Id
    @GeneratedValue
    private Integer id;
    private Long balance;

    public LoyaltyCard(Long balance) {
        this.balance = balance;
    }
}
