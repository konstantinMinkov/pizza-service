package ua.rd.pizzaservice.domain;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@NoArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue
    private Integer id;
    private String address;

    public Address(String address) {
        this.address = address;
    }
}
