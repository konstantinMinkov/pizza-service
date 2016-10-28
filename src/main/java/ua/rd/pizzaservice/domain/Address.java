package ua.rd.pizzaservice.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.TableGenerator;


@Data
@NoArgsConstructor
@Entity
public class Address {

    @TableGenerator(name="address_gen")
    @Id @GeneratedValue(generator="address_gen")
    private Long id;
    private String address;

    public Address(String address) {
        this.address = address;
    }
}
