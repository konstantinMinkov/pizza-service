package ua.rd.pizzaservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.concurrent.atomic.AtomicLong;


@Data
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToOne
    @Cascade({CascadeType.PERSIST, CascadeType.MERGE})
    private Address address;
    @OneToOne
    @Cascade({CascadeType.PERSIST, CascadeType.MERGE})
    private LoyaltyCard loyaltyCard;

    public Customer(String name, Address address, LoyaltyCard card) {
        this.name = name;
        this.address = address;
        this.loyaltyCard = card;
    }
}
