package ua.rd.pizzaservice.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@NoArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue
    private Long id;
    private String address;

    public Address(String address) {
        this.address = address;
    }
}
