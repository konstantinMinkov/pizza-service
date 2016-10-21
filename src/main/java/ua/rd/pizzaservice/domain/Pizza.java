package ua.rd.pizzaservice.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
public class Pizza implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name;
    private Long price;
    @Enumerated(EnumType.STRING)
    private PizzaType type;

    public Pizza() {}

    public Pizza(String name, Long price, PizzaType type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public Pizza(Integer id, String name, Long price, PizzaType type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
    }
}
