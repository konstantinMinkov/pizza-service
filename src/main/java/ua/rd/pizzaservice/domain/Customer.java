package ua.rd.pizzaservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicLong;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private static AtomicLong lastId = new AtomicLong(-1);

    private final Long id = lastId.incrementAndGet();
    private String name;
    private Address address;
    private LoyaltyCard loyaltyCard;
}
