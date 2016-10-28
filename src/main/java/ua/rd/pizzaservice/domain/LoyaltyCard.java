package ua.rd.pizzaservice.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.TableGenerator;


@Data
@NoArgsConstructor
@Entity
public class LoyaltyCard {

    @TableGenerator(name="card_gen")
    @Id @GeneratedValue(generator="card_gen")
    private Long id;
    private Long balance;

    public LoyaltyCard(Long balance) {
        this.balance = balance;
    }
}
