package ua.rd.pizzaservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.concurrent.atomic.AtomicLong;


@Data
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToOne
    private Address address;
    @OneToOne
    private LoyaltyCard loyaltyCard;

    public Customer(String name, Address address, LoyaltyCard card) {
        this.name = name;
        this.address = address;
        this.loyaltyCard = card;
    }
}
